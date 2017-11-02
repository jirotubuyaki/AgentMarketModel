package com.github.jirotubuyaki.main.scala.Company

import com.github.jirotubuyaki.main.scala.Market._
import com.github.jirotubuyaki.main.scala.Company._
import com.github.jirotubuyaki.main.scala.Product._
import com.github.jirotubuyaki.main.scala.Customer._
import com.github.jirotubuyaki.main.scala.Need._
import java.io._
import scala.io._
import scala.math._
import scala.collection.mutable._
import breeze.linalg._

class Company{
  var id: Int = 0
  var capital: Int = 10000
  var profit :Double = 0
  var profitRate: Double = 0.7
  var market: Market = null

  var productCostAll: Double = 0

  var designCost: ListBuffer[Int] = ListBuffer[Int]()
  var designProductionCost: Double = 0
  var funcsCost: ListBuffer[Int] = ListBuffer[Int]()
  var funcsProductionCost: Double = 0
  var channelCost: ListBuffer[Int] = ListBuffer[Int]()
  var channelProductionCost: Double = 0

  var historyOfSell: ListBuffer[Int] = ListBuffer[Int]()
  var product_objs: ListBuffer[Product] = ListBuffer[Product]()

  def Init(parent_obj: Market, order: Int):Unit = {
    id = order
    market = parent_obj

    //println(designProduct)
    //println(funcsProduct)
    //println(channelProduct)
  }
  def MarketSearch():Unit = {

  }
  def Advertise():Unit = {
    for(i <- 0 to market.customerNum - 1; j <- 0 to product_objs.length - 1){
      market.customer_objs(i).WatchAdvertise(this, product_objs(j))
    }
  }
  def Product():Unit = {
    designProductionCost = 0
    funcsProductionCost = 0
    channelProductionCost = 0

    var product_obj: Product = new Product()
    product_obj.Init(market, this, id)
    product_objs = product_objs :+ product_obj

    for(i <- 0 to product_obj.designProductNum-1){designProductionCost += market.designCost(i) * product_obj.designProduct(i)}
    for(i <- 0 to product_obj.funcsProductNum-1){funcsProductionCost += market.funcsCost(i) * product_obj.funcsProduct(i)}
    for(i <- 0 to product_obj.channelProductNum-1){channelProductionCost += market.channelCost(i) * product_obj.channelProduct(i)}

    productCostAll = designProductionCost + funcsProductionCost + channelProductionCost
    product_obj.sellPrice = (productCostAll / profitRate)
    //println("cost:" + productCostAll)
  }
  def Sell(order_id: Int):Unit = {
    historyOfSell += order_id
    profit = profit + product_objs(0).sellPrice - productCostAll
  }
}
