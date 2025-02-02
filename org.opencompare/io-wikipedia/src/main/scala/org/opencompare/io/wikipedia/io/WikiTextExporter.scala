package org.opencompare.io.wikipedia.io

import java.io.{InputStream, FileInputStream, BufferedInputStream}

import org.opencompare.api.java.{Cell, PCMMetadata, PCMContainer, PCM}
import org.opencompare.api.java.io.PCMExporter

import scala.collection.JavaConversions._

/**
 * Created by gbecan on 26/11/14.
 */
class WikiTextExporter(exportRawContent : Boolean = false)  extends PCMExporter {

  // Constructor for Java compatibility with default parameters
  def this() {
    this(false)
  }

  def exportWithProductAsLines(builder : StringBuilder, container : PCMContainer){

    // columns (feature)
    for (feature <- container.getMetadata.getSortedFeatures) {
      builder ++= "! " // new header
      builder ++= feature.getName
      builder ++= "\n"
    }

    // Lines (products)
    for (product <- container.getMetadata.getSortedProducts) {

      // Product name header
      builder ++= "|-\n"
      builder ++= "! "
      builder ++= product.getName
      builder ++= "\n"

      // Cells
      for (feature <- container.getMetadata.getSortedFeatures) {
        val cell : Cell = product.findCell(feature)
        builder ++= "| " // new cell (we can also use || to separate cells horizontally)
        if (exportRawContent) {
          builder ++= cell.getRawContent
        } else {
          builder ++= cell.getContent
        }
        builder ++= "\n"
      }
    }
  }

  def exportWithFeatureAsLines(builder : StringBuilder, container : PCMContainer) {

    // columns (product)
    for (product <- container.getMetadata.getSortedProducts) {
      builder ++= "! " // new header
      builder ++= product.getName
      builder ++= "\n"
    }

    // Lines (feature)
    for (feature <- container.getMetadata.getSortedFeatures) {

      // Feature name header
      builder ++= "|-\n"
      builder ++= "! "
      builder ++= feature.getName
      builder ++= "\n"

      // Cells
      for (product <- container.getMetadata.getSortedProducts) {
        val cell : Cell = product.findCell(feature)
        builder ++= "| " // new cell (we can also use || to separate cells horizontally)
        if (exportRawContent) {
          builder ++= cell.getRawContent
        } else {
          builder ++= cell.getContent
        }
        builder ++= "\n"
      }
    }
  }

  override def export(container: PCMContainer): String = {
    val builder = new StringBuilder
    val pcm = container.getPcm

    builder ++= "{| class=\"wikitable\"\n" // new table
    val title = pcm.getName
    builder ++= "|+ " + title + "\n" // caption

    // Headers
    builder ++= "|-\n" // new row
    builder ++= "|\n" // empty top left cell

    if (container.getMetadata.getProductAsLines) {
      exportWithProductAsLines(builder, container)
    } else {
      exportWithFeatureAsLines(builder, container)
    }
    builder ++= "|}" //  end table

    builder.toString()
  }

}
