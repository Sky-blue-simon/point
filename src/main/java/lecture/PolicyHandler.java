package lecture;

import java.util.List;
import java.util.Optional;

import lecture.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_RegisterPoint(@Payload PaymentApproved paymentApproved){

        if(paymentApproved.isMe()){
            Point point = new Point();
            
            point.setId(paymentApproved.getId());
            point.setClassId(paymentApproved.getClassId());
            point.setFee(paymentApproved.getFee());
            point.setStudent(paymentApproved.getStudent());

            // Cash Point
            Long lcp;
            lcp = (Long)paymentApproved.getFee();
            lcp = lcp / 100;
            
            point.setCashpoint(lcp);

            pointRepository.save(point);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_CancelPoint(@Payload PaymentCanceled paymentCanceled){

        if (paymentCanceled.isMe()) {
            List<Point> pointList = pointRepository.findByClassId(paymentCanceled.getClassId());

            for (Point point:pointList)
            {
                Long lcp;
                lcp = Long.parseLong("0");
                point.setCashpoint(lcp);
                pointRepository.save(point);
            }

        }


    }

}
