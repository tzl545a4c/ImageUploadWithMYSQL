package Tools;

import java.util.ArrayList;

public class FuzzyController 
{
	private double Priority = 0.0;
	private double ErrorValue = 0.0;
	private double deErrorValue = 0.0;
	private double ErrorValueRange = 0.0;
	private double deErrorValueRange = 0.0;
	private double Delta = 0.0;
	
	public FuzzyController(double _Priority, double _ErrorValue, double _ErrorValueRate, double _ErrorValueRange, double _deErrorValueRange, double _Delta)
	{
		this.Priority = _Priority;
		this.ErrorValue = _ErrorValue;
		this.deErrorValue = _ErrorValueRate;
		this.ErrorValueRange = _ErrorValueRange;
		this.deErrorValueRange = _deErrorValueRange;
		this.Delta = _Delta;
	}
	
	public double Execute()
	{
		double Priority_New = 0.0;
		double Range_Pos = 0.0, Range_Neg = 0.0, deRange_Pos = 0.0, deRange_Neg = 0.0;
		double Value_M = 0.0, Value_S = 0.0, Value_L = 0.0;
		double mu_P = 0.0, mu_N = 0.0, mu_deP = 0.0, mu_deN = 0.0, mu_Total = 0.0;
		ArrayList<Double> mu_List = new ArrayList<Double>();
		
		//----- Set Domain Range -----//
		Range_Pos = this.ErrorValueRange * 0.75;
		Range_Neg = -Range_Pos;
		deRange_Pos = this.deErrorValueRange * 0.75;
		deRange_Neg = -deRange_Pos; 
		//----------------------------//

		//----- Count Fuzzy Singletons -----//
		Value_M = this.Priority;
		Value_S = this.Priority + this.Delta;
		Value_L = this.Priority + this.Delta * 2;
		//----------------------------------//

		//----- Count mu_N -----//
		if(this.ErrorValue >= 0)
		{ mu_N = 0; }
		else if(this.ErrorValue <= Range_Neg)
		{ mu_N = 1; }
		else if(this.ErrorValue > Range_Neg & this.ErrorValue < 0)
		{ mu_N = this.ErrorValue / Range_Neg; }
		//----------------------//
		
		//----- Count mu_P -----//
		if(this.ErrorValue >= Range_Pos)
		{ mu_P = 1; }
		else if(this.ErrorValue <= 0) 
		{ mu_P = 0; }
		else if(this.ErrorValue < Range_Pos & this.ErrorValue > 0)
		{ mu_P = this.ErrorValue / Range_Pos; }
		//----------------------//

		//----- Count mu_deN -----//
		if(this.deErrorValue >= deRange_Pos)
		{ mu_deP = 0; }
		else if(this.deErrorValue <= deRange_Neg)
		{ mu_deP = 1; }
		else if(this.deErrorValue < Range_Pos & this.deErrorValue > deRange_Neg)
		{ mu_deP = (this.deErrorValue - deRange_Pos) / (deRange_Pos * 2); }
		//------------------------------//
		
		//----- Count mu_deP -----//
		if(this.deErrorValue >= deRange_Pos)
		{ mu_deP = 1; }
		else if(this.deErrorValue <= deRange_Neg)
		{ mu_deP = 0; }
		else if(this.deErrorValue < Range_Pos & this.deErrorValue > deRange_Neg)
		{ mu_deP = (this.deErrorValue - deRange_Neg) / (deRange_Pos * 2); }
		//------------------------------//
		
		//----- Count mu_1, mu_2, mu_3, mu_4 -----//
		mu_List.add(Math.min(mu_P, mu_deP));
		mu_List.add(Math.min(mu_P, mu_deN));
		mu_List.add(Math.min(mu_N, mu_deP));
		mu_List.add(Math.min(mu_N, mu_deN));
		//----------------------------------------//
		
		mu_Total = mu_List.get(0) + mu_List.get(1) + mu_List.get(2) + mu_List.get(3);
		
		if(mu_Total != 0)
		{ Priority_New = (mu_List.get(0) * Value_S + mu_List.get(1) * Value_M + mu_List.get(2) * Value_M + mu_List.get(3) * Value_L) / mu_Total; }
		else
		{ Priority_New = 0; } 
		
		return Priority_New;
	}
}
