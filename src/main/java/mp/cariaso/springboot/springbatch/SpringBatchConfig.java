package mp.cariaso.springboot.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    @Qualifier("beanItemWriter")
    private ItemWriter<FileReadBean> beanItemWriter;

    @Bean
    public Job asyncMultiStepJob() {

        return jobBuilderFactory.get("asyncMultiStepJob")
                .start(firstFlow())
                .split(new SimpleAsyncTaskExecutor())
                .add(secondFlow(), thirdFlow())
                .end()
                .build();
    }

    @Bean
    public Job firstStepJob() {

        return jobBuilderFactory.get("firstStepJob")
                .start(firstStep())
                .build();
    }

    @Bean
    public Flow firstFlow() {
        return new FlowBuilder<Flow>("firstFlow").start(firstStep()).build();
    }

    @Bean
    public Flow secondFlow() {
        return new FlowBuilder<Flow>("secondFlow").start(secondStep()).build();
    }

    @Bean
    public Flow thirdFlow() {
        return new FlowBuilder<Flow>("thirdFlow").start(secondStep()).build();
    }

    @Bean
    public Step firstStep() {

        return stepBuilderFactory.get("firstStep")
                .<FileReadBean, FileReadBean>chunk(100)
                .reader(flatFileItemReader(null)) //the param will be injected
                .processor(fileBeanProcessor())
                .writer(beanItemWriter)
                .build();
    }

    @Bean
    public Step secondStep() {

        return stepBuilderFactory.get("secondStep")
                .<FileReadBean, FileReadBean>chunk(100)
                .reader(flatFileItemReader(null))
                .processor(fileBeanProcessor())
                .writer(beanItemWriter)
                .build();
    }

    public FlatFileItemReader<FileReadBean> flatFileItemReader(@Value("#{jobParameters[paramName]}") String paramName) {

        String file = "file:/some/path/file.txt";

        FlatFileItemReader<FileReadBean> reader = new FlatFileItemReader();
        reader.setResource(resourceLoader.getResource(file));
        reader.setLinesToSkip(0);
        reader.setStrict(false);
        reader.setLineMapper(beanLineMapper());

        return reader;
    }

    @Bean
    public ItemProcessor<FileReadBean, FileReadBean> fileBeanProcessor() {
        return new FileBeanProcessor();
    }

    @Bean
    public LineMapper<FileReadBean> beanLineMapper() {

        DefaultLineMapper<FileReadBean> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(beanLineTokenizer());
        mapper.setFieldSetMapper(beanFieldSetMapper());
        return mapper;
    }

    @Bean
    public LineTokenizer beanLineTokenizer() {

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(getColumnRanges());
        tokenizer.setNames(getColumnNames());

        return tokenizer;
    }

    @Bean
    public FieldSetMapper<FileReadBean> beanFieldSetMapper() {

        return new BeanFieldSetMapper();
    }

    @Bean
    public JobLauncher asyncJobLauncher() {

        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(threadPoolTaskExecutor());

        return jobLauncher;
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setMaxPoolSize(8);
        exec.setCorePoolSize(4);
        exec.setQueueCapacity(200);

        return exec;
    }

    private Range[] getColumnRanges() {

        return new Range[] {
                new Range (1, 10),
                new Range (1, 10)
        };
    }

    private String[] getColumnNames() {

        return new String[] {
                "fullName",
                "emailAddress"
        };
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        //prevent spring batch from initializing DB metadata
    }
}
