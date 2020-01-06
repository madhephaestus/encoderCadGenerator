import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "encoder"
	if(args==null)
		args=["AMT102_V"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def bodyZValue = measurments.bodyZ
	def diameterOfWingsBoltHoleValue = measurments.diameterOfWingsBoltHole
	def centerToX_EdgeValue = measurments.centerToX_Edge
	def wingsZValue = measurments.wingsZ
	def wingsYValue = measurments.wingsY
	def boltSizeValue = measurments.boltSize
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
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 