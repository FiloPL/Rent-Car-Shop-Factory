package FiloPL.carrentshop.rent_point;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RentPointService {

    private final RentPointRepository rentPointRepository;


    public RentPointService(RentPointRepository rentPointRepository) {
        this.rentPointRepository = rentPointRepository;
    }


    public Set<RentPoint> getAllRentPoints() {
        return new HashSet<>(rentPointRepository.findAll());
    }


    public RentPoint getRentPointById(int rentPointId) {

        return rentPointRepository.findById(rentPointId)
                .orElseThrow(() -> new RentPointNotExistException("rentPointId:" + rentPointId));
    }


    public RentPoint saveOrUpdateRentPoint(RentPoint rentPoint) {
        return rentPointRepository.save(rentPoint);
    }
}
