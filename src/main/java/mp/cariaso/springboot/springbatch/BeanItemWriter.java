package mp.cariaso.springboot.springbatch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("beanItemWriter")
@Transactional
public class BeanItemWriter implements ItemWriter<FileReadBean> {

    @Override
    public void write(List<? extends FileReadBean> list) throws Exception {

        //DO ANYTHING HERE. WRITE NEW FILE, SAVE TO DATABASE, ETC
    }
}
