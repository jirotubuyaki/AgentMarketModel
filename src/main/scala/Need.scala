package jp.jirotubuyaki.main.scala.Need

import jp.jirotubuyaki.main.scala.Market._
import jp.jirotubuyaki.main.scala.Company._
import jp.jirotubuyaki.main.scala.Product._
import jp.jirotubuyaki.main.scala.Customer._
import jp.jirotubuyaki.main.scala.Need._
import java.io._
import scala.io._
import scala.math._
import scala.collection.mutable._
import breeze.linalg._
import breeze.stats.distributions._

class Need{
  var market: Market = null
  var customer_obj :Customer = null

  var customer_id: Int = 0

  var designNeedsNum: Int = 0
  var funcsNeedsNum: Int = 0
  var channelNeedsNum: Int = 0

  var acceptPrice: Int = 0

  var designNeeds: ListBuffer[Int] = ListBuffer[Int]()
  var funcsNeeds: ListBuffer[Int] = ListBuffer[Int]()
  var channelNeeds: ListBuffer[Int] = ListBuffer[Int]()

  var designParameters: ListBuffer[Int] = ListBuffer[Int]()
  var funcsParameters: ListBuffer[Int] = ListBuffer[Int]()
  var channelParameters: ListBuffer[Int] = ListBuffer[Int]()

  var a: ListBuffer[Double] = ListBuffer[Double]()
  var b: ListBuffer[Double] = ListBuffer[Double]()
  var beta_param: ListBuffer[Double] = ListBuffer[Double]()
  var beta: Beta = null

  def Init(market_obj: Market, parent_obj: Customer, order: Int):Unit = {
    market = market_obj
    customer_obj = parent_obj
    customer_id = order

    designNeedsNum = market.designNum
    funcsNeedsNum = market.funcsNum
    channelNeedsNum = market.channelNum

    val normal = breeze.stats.distributions.Gaussian(500, 75)
    acceptPrice = normal.sample(1)(0).toInt

    if(customer_obj.age <= 20){
      a += 1.0
      b += 2.0

      a += 1.0
      b += 4.0

      a += 1.0
      b += 6.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      for(i <- 0 to a.length - 1){
        var beta: Beta = new Beta(a(i),b(i))
        beta_param += beta.sample(1)(0)
      }
      for(i <- 10 to a.length*2 - 1){
        var beta: Beta = new Beta(a(i-10),b(i-10))
        beta_param += beta.sample(1)(0)
      }
    }
    else if(customer_obj.age <= 40){
      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 1.0
      b += 2.0

      a += 1.0
      b += 4.0

      a += 1.0
      b += 6.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      for(i <- 0 to a.length - 1){
        var beta: Beta = new Beta(a(i),b(i))
        beta_param += beta.sample(1)(0)
      }
      for(i <- 10 to a.length*2 - 1){
        var beta: Beta = new Beta(a(i-10),b(i-10))
        beta_param += beta.sample(1)(0)
      }
    }
    else if(customer_obj.age <= 60){
      a += 20.0
      b += 10.0

      a += 50.0
      b += 1.0

      a += 5.0
      b += 1.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 1.0
      b += 2.0

      a += 1.0
      b += 40.0

      a += 1.0
      b += 60.0

      a += 10.0
      b += 10.0

      for(i <- 0 to a.length - 1){
        var beta: Beta = new Beta(a(i),b(i))
        beta_param += beta.sample(1)(0)
      }
      for(i <- 10 to a.length*2 - 1){
        var beta: Beta = new Beta(a(i-10),b(i-10))
        beta_param += beta.sample(1)(0)
      }
      println(beta_param)
    }
    else if(customer_obj.age <= 80){
      a += 1.0
      b += 40.0

      a += 1.0
      b += 6.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 1.0
      b += 40.0

      a += 1.0
      b += 60.0

      for(i <- 0 to a.length - 1){
        var beta: Beta = new Beta(a(i),b(i))
        beta_param += beta.sample(1)(0)
      }
      for(i <- 10 to a.length*2 - 1){
        var beta: Beta = new Beta(a(i-10),b(i-10))
        beta_param += beta.sample(1)(0)
      }
    }
    else{
      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      a += 1.0
      b += 20.0

      a += 1.0
      b += 4.0

      a += 1.0
      b += 60.0

      a += 1.0
      b += 4.0

      a += 1.0
      b += 20.0

      a += 1.0
      b += 30.0

      a += 10.0
      b += 10.0

      a += 10.0
      b += 10.0

      for(i <- 0 to a.length - 1){
        var beta: Beta = new Beta(a(i),b(i))
        beta_param += beta.sample(1)(0)
      }
      for(i <- 10 to a.length*2 - 1){
        var beta: Beta = new Beta(a(i-10),b(i-10))
        beta_param += beta.sample(1)(0)
      }
    }
    for(i <- 0 to designNeedsNum - 1) if((new Bernoulli(beta_param(i)).sample(1)(0)) == true){designNeeds += 1} else{designNeeds += 0}
    for(i <- 0 to funcsNeedsNum - 1) if((new Bernoulli(beta_param(i)).sample(1)(0)) == true){funcsNeeds += 1} else{funcsNeeds += 0}
    for(i <- 0 to channelNeedsNum - 1) if(random * 10 <= 5){channelNeeds += 1} else{channelNeeds += 0}

    for(i <- 0 to designNeedsNum - 1) {designParameters += (b(i) * 1).toInt}
    for(i <- 0 to funcsNeedsNum - 1) {funcsParameters += (b(i) * 1).toInt}
    for(i <- 0 to channelNeedsNum - 1) {channelParameters += (b(i) * 1).toInt}

    //println(designProduct)
    //println(funcsProduct)
    //println(channelProduct)
  }
}
