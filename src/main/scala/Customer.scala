package jp.jirotubuyaki.main.scala.Customer

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

class Customer{
  var market: Market = null
  var id: Int = 0
  var age: Int = 0
  var sex: Int = 0
  var work: Int = 0
  var education: Int = 0

  var utilityforProductsMemory: Double = -10000
  var bestCompany: Company = null

  var historyOfBuy: ListBuffer[Int] = ListBuffer[Int]()
  var need_objs: ListBuffer[Need] = ListBuffer[Need]()

  def Init(parent_obj: Market, order: Int):Unit = {
    id = order
    market = parent_obj

    val normal = breeze.stats.distributions.Gaussian(35, 10)
    age = normal.sample(1)(0).toInt

    sex = if(random*10 <= 5) {1} else {0}
    education = if((random*10) <= 3.3) {2} else if((random*10 <= 5)) {1} else{0}

    var need_obj = new Need()
    need_obj.Init(market, this, id)
    need_objs = need_objs :+ need_obj

    //println(age)
    //println(sex)
    //print(education)
    //println(designNeeds)
    //println(funcsNeeds)
    //println(channelNeeds)
  }
  def WatchAdvertise(knowCompany: Company, product_obj: Product):Unit = {
    for(j <- 0 to need_objs.length - 1){
      var utilityforProducts: Double = 0
      for(i <- 0 to need_objs(j).designNeedsNum - 1){
        if(need_objs(j).designNeeds(i) == product_obj.designProduct(i)){
          utilityforProducts = utilityforProducts + need_objs(j).designParameters(i)
        }
      }
      for(i <- 0 to need_objs(j).funcsNeedsNum - 1){
        if(need_objs(j).funcsNeeds(i) == product_obj.funcsProduct(i)){
          utilityforProducts = utilityforProducts + need_objs(j).funcsParameters(i)
        }
      }
      for(i <- 0 to need_objs(j).channelNeedsNum - 1){
        if(need_objs(j).channelNeeds(i) == product_obj.channelProduct(i)){
          utilityforProducts = utilityforProducts + need_objs(j).channelParameters(i)
        }
      }
      utilityforProducts = utilityforProducts - (product_obj.sellPrice - need_objs(j).acceptPrice)
      //println("company:"+ knowCompany.id + "  utility:" + utilityforProducts + "\n")
      Compare(knowCompany, utilityforProducts)
    }
  }
  def Buy():Unit = {
    bestCompany.Sell(id)

  }
  def Compare(knowCompany: Company, utility: Double):Unit = {
    if(utilityforProductsMemory <= utility){
      utilityforProductsMemory = utility
      bestCompany = knowCompany
      //println("id:" + bestCompany.id + "\n")
    }

  }
}
