import com.leo.cloud.bo.FeedBo;
import com.leo.cloud.pojo.FeedData;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


/*@SpringBootTest*/
public class EurkaServerTest {

    @Test
    public void testCompu(){
        int a = 23;
        int b =10;

        int c = 2;
        System.out.println(c<<9);
    }




    @Test
    public void test(){

        List<FeedBo> lst = Lists.newArrayList();
        getFeedDataList(lst,FeedBo.class);

    }

    private void getFeedDataList(List<FeedBo> lst,Class cls){



        List<String> clsFieldNames = Arrays.stream(cls.getDeclaredFields()).map(f->f.getName()).collect(Collectors.toList());
        List<String> includeFieldNames = Arrays.stream(FeedBo.class.getDeclaredFields()).filter(bo-> clsFieldNames.contains(bo.getName()))
                .map(bo->bo.getName()).collect(Collectors.toList());


        List<FeedData> datas = new ArrayList<>();
        for(int i = 0; i<10;i++){
            FeedData data = new FeedData();
            data.setId(Long.valueOf(i));
            data.setName("Number"+i);
            data.setCreateDate(new Date());
            datas.add(data);
        }


        datas.stream().forEach(d ->{
            FeedBo bo = new FeedBo();
            bo.setId(d.getId());
            lst.add(bo);
        });


        datas.stream().forEach(d->{
            Optional<FeedBo> bo = lst.stream().filter(b-> b.getId().longValue() == d.getId()).findFirst();
            if(bo.isPresent()){
                includeFieldNames.stream().forEach( fieldName ->{
                    try {
                        Field targetField = cls.getDeclaredField(fieldName);
                        Field sourceField = d.getClass().getDeclaredField(fieldName);

                        sourceField.setAccessible(true);
                        targetField.setAccessible(true);
                        targetField.set(bo.get(),sourceField.get(d));

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }
        });


        System.out.println(1111);
    }
}
