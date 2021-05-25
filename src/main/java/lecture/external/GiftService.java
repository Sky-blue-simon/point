
package lecture.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name="gift", url="http://localhost:8091")
public interface GiftService {

    @RequestMapping(method= RequestMethod.GET, path="/chkAndModifyGiftStock")
    public boolean modifyGiftStock(@RequestParam("student") String student,
                                   @RequestParam("classId") Long classId,
                                   @RequestParam("fee") Long fee);
    //public boolean modifyGiftStock(@RequestBody Gift gift);


}