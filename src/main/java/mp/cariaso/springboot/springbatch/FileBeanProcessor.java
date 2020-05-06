package mp.cariaso.springboot.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

//@Component
public class FileBeanProcessor implements ItemProcessor<FileReadBean, FileReadBean> {

    @Override
    public FileReadBean process(FileReadBean fileReadBean) throws Exception {

        //do something here
        return fileReadBean;
    }
}
