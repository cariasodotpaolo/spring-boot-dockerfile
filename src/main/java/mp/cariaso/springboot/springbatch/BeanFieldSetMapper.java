package mp.cariaso.springboot.springbatch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BeanFieldSetMapper implements FieldSetMapper<FileReadBean>  {

    @Override
    public FileReadBean mapFieldSet(FieldSet fieldSet) throws BindException {

        FileReadBean bean = new FileReadBean();
        bean.setEmailAddress(fieldSet.readString("emailAddress"));
        bean.setFullName(fieldSet.readString("fullName"));

        return null;
    }
}
