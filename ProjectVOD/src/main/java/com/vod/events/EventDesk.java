package com.vod.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vod.project.PriceException;
import com.vod.project.Product;

public class EventDesk {
	private Product product;
	private List processes= new ArrayList();
	
	public Product getProduct() {
		return product;
		}
	public void setProduct(Product product) {
		this.product = product;
		}
	
	
	
	
	public synchronized void addProcess(EventProductProcesses process){
		processes.add(process);
	}
	public synchronized void removeProcess(EventProductProcesses process){
		processes.remove(process);
	}	
	public synchronized void executeProcesses(){
		
	EventProduct Event=new EventProduct(this,product);
	Iterator proc = processes.iterator();
	while(proc.hasNext()){
		( (EventProductProcesses) proc.next() ).processProduct(Event);
	}
	}
		
public static class CleanProduct implements EventProductProcesses
{
		public void processProduct(EventProduct Event){
		System.out.println(Event.getProduct().getName()+" produkt zosta³ wyczyszczony.");
			}
}


public static class ChangeBoxProduct implements EventProductProcesses{
	public void processProduct(EventProduct Event){
		if(Event.getProduct().getBox())
		System.out.println(Event.getProduct().getName()+" box zosta³ zmieniony.");
	}
}

public static class PromoteProduct implements EventProductProcesses{
	public void processProduct(EventProduct Event){
		System.out.println(Event.getProduct().getName()+" jest 25% tañszy dziœ!.");
		double z=Event.getProduct().getPrice();
		z=(double)((double)(z/25)*9);
		try {
			Event.getProduct().setPrice(z);
		} catch (PriceException e) {
		}
	}
	}
	public static class RollbackProduct implements EventProductProcesses{
		public void processProduct(EventProduct Event){
			System.out.println(Event.getProduct().getName()+" przywrocony do standardowej ceny.");
			double z=Event.getProduct().getPrice();
			z=(double)((double)(z*10)/9);
			try {
				Event.getProduct().setPrice(z);
			} catch (PriceException e) {

			}
		}
		}
	
	
	
	
	
	
	
	
}
