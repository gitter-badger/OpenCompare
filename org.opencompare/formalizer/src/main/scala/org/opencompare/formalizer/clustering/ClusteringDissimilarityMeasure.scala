package org.opencompare.formalizer.clustering

import ch.usi.inf.sape.hac.experiment.Experiment
import ch.usi.inf.sape.hac.experiment.DissimilarityMeasure

class ClusteringDissimilarityMeasure[T]
(val dissimilarityMetric : (T,T) => Double)
extends DissimilarityMeasure {

  
	override def computeDissimilarity(experiment : Experiment, observation1 : Int, observation2 : Int) : Double = {
		val clusteringExperiment = experiment.asInstanceOf[ClusteringExperiment[T]]
		val value1 = clusteringExperiment.getObservation(observation1)
		val value2 = clusteringExperiment.getObservation(observation2)
		
		dissimilarityMetric(value1, value2)
	}
  
}