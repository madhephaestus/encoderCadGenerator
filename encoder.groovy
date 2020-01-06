import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "encoder"
	if(args==null)
		args=["AMT102_V"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	LengthParameter boltLength		= new LengthParameter("Bolt Length",10,[180,10])
	boltLength.setMM(10)
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def bodyZValue = measurments.bodyZ
	def diameterOfWingsBoltHoleValue = measurments.diameterOfWingsBoltHole
	def centerToX_EdgeValue = measurments.centerToX_Edge
	def wingsZValue = measurments.wingsZ
	def wingsYValue = measurments.wingsY
	String boltSizeValue = measurments.boltSize
	def wingsXValue = measurments.wingsX
	def bodyXValue = measurments.bodyX
	def shaftDiameterValue = measurments.shaftDiameter
	def bodyYValue = measurments.bodyY
	println "Measurment bodyZValue =  "+bodyZValue
	println "Measurment diameterOfWingsBoltHoleValue =  "+diameterOfWingsBoltHoleValue
	println "Measurment centerToX_EdgeValue =  "+centerToX_EdgeValue
	println "Measurment wingsZValue =  "+wingsZValue
	println "Measurment wingsYValue =  "+wingsYValue
	println "Measurment boltSizeValue =  "+boltSizeValue
	println "Measurment wingsXValue =  "+wingsXValue
	println "Measurment bodyXValue =  "+bodyXValue
	println "Measurment shaftDiameterValue =  "+shaftDiameterValue
	println "Measurment bodyYValue =  "+bodyYValue
	CSG vitamin_capScrew_M25 = Vitamins.get("capScrew", boltSizeValue)
							.movez(wingsZValue)

	CSG wings = new Cube(wingsXValue,wingsYValue,wingsZValue).toCSG()
					.toZMin()
					.union(vitamin_capScrew_M25.movey(diameterOfWingsBoltHoleValue/2))
					.union(vitamin_capScrew_M25.movey(-diameterOfWingsBoltHoleValue/2))
			
	CSG part = new Cube(bodyXValue,bodyYValue,bodyZValue).toCSG()
					.toXMax()
					.movex(centerToX_EdgeValue)
					.toZMin()
					.union(wings)
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate()