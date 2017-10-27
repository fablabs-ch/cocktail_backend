package ch.fablabs.fabjam.cocktail.service;

import ch.fablabs.fabjam.cocktail.data.serial.SerialStatus;
import ch.fablabs.fabjam.cocktail.data.type.JmsTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

@Service
public class CarierService {

	private Subject<Integer, Integer> distanceSubject;

	public CarierService() {
		this.distanceSubject = BehaviorSubject.create(-1);
	}

	public Observable<Integer> distanceFromHomeInMm() {
		return distanceSubject;
	}

	@JmsListener(destination = JmsTopic.SERIAL_STATUS)
	protected void serialStatus(SerialStatus status) {
		this.distanceSubject.onNext(status.getCarrierDistMm());
	}
}
