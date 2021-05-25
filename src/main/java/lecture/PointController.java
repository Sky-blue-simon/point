package lecture;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

 @RestController
 public class PointController {

    @Autowired
    PointRepository pointRepository;

    @PostMapping(value = "/point")
    public Point registerClass(@RequestBody Map<String, String> param) {

        Point pt = new Point();
        
        pt.setClassId(Long.parseLong(param.get("classId")));
        pt.setFee(Long.parseLong(param.get("Fee")));
        pt.setStudent(param.get("student"));
        // pt.setCashpoint(Long.parseLong(param.get("Fee")));
        
        pt = pointRepository.save(pt);

        return pt;
    }

    @PatchMapping(value = "/point/{id}")
    public Point modifyPoint(@RequestBody Map<String, String> param, @PathVariable String id) {

        Optional<Point> opt = pointRepository.findById(Long.parseLong(id));
        Point pt = null;

        if (opt.isPresent()) {
            pt = opt.get();

            pt = pointRepository.save(pt);
        }

        return pt;
    }

    @PutMapping(value = "/point/{id}")
    public Point modifyPointPut(@RequestBody Map<String, String> param, @PathVariable String id) {
        return this.modifyPoint(param, id);
    }

    @GetMapping(value = "/point/{id}")
    public Point inquiryPointById(@PathVariable String id) {

        Optional<Point> opt = pointRepository.findById(Long.parseLong(id));
        Point pt = null;

        if (opt.isPresent())
            pt = opt.get();

        return pt;
    }


    @DeleteMapping(value = "/point/{id}")
    public boolean deletePoint(@PathVariable String id) {
        boolean result = false;

        pointRepository.deleteById(Long.parseLong(id));
        result = true;

        return result;
    }

 }
