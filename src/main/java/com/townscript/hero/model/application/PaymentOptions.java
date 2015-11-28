package com.townscript.hero.model.application;


public enum PaymentOptions {

	PAYUMONEY{

		@Override
		public Integer getIndex() {
			return 0;
		}

		@Override
		public String getPaymentSolutionSource() {
			return "PAYUMONEY";
		}
		
	},
	CITRUSPAY{

		@Override
		public Integer getIndex() {
			return 1;
		}

		@Override
		public String getPaymentSolutionSource() {
			return "CITRUSPAY";
		}
	},
	CCDBCARD{

		@Override
		public Integer getIndex() {
			return 2;
		}

		@Override
		public String getPaymentSolutionSource() {
			return "CCDBCARD";
		}
	};
	
	public abstract Integer getIndex();

	
	public abstract String getPaymentSolutionSource();


	public static PaymentOptions getPaymentSolutionSource(Integer index) {
		
		PaymentOptions paymentSolution = null;
		
		for(PaymentOptions type : PaymentOptions.values()) {
			 if(type.getIndex()==index){
				 paymentSolution = type;
                 break;
         }
		}
		return paymentSolution;
	}
}
