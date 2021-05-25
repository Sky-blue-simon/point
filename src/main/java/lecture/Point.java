package lecture;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Point_table")
public class Point {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long classId;
    private Long fee;
    private String student;
    private Long cashpoint;

    @PostPersist
    public void onPostPersist(){
/*        
        PointRegistered pointRegistered = new PointRegistered();
        BeanUtils.copyProperties(this, pointRegistered);
        pointRegistered.publishAfterCommit();
*/


        Point point = new Point();

        // mappings goes here
        point.setClassId(this.getId());
        point.setFee(this.getFee());
        point.setStudent(this.getStudent());
        point.setCashpoint(this.getCashpoint());

        boolean rslt = PointApplication.applicationContext.getBean(lecture.external.GiftService.class)
                                       .modifyGiftStock(this.getStudent(), this.getClassId(), this.getFee());

        if(rslt) {
            PointRegistered poitregistered = new PointRegistered();
            BeanUtils.copyProperties(this, poitregistered);
            poitregistered.publishAfterCommit();
        }

    }


    @PreRemove
    public void onPreRemove(){
        PointCanceled pointCanceled = new PointCanceled();
        BeanUtils.copyProperties(this, pointCanceled);
        pointCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
    public Long getCashpoint() {
        return cashpoint;
    }

    public void setCashpoint(Long cashpoint) {
        this.cashpoint = cashpoint;
    }




}
