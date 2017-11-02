package com.github.jirotubuyaki.main.scala.Product

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

class Product{
    var market: Market = null
    var company_obj :Company = null

    var company_id: Int = 0
    var sellPrice: Double = 0;

    var designProductNum: Int = 0
    var funcsProductNum: Int = 0
    var channelProductNum: Int = 0

    var designProduct: ListBuffer[Int] = ListBuffer[Int]()
    var funcsProduct: ListBuffer[Int] = ListBuffer[Int]()
    var channelProduct: ListBuffer[Int] = ListBuffer[Int]()

    def Init(market_obj: Market, parent_obj: Company, order: Int):Unit = {
      market = market_obj
      company_obj = parent_obj
      company_id = order

      designProductNum = market.designNum
      funcsProductNum = market.funcsNum
      channelProductNum = market.channelNum

      for(i <- 0 to designProductNum-1) if(random*10 <= 5){designProduct += 1} else{designProduct += 0}
      for(i <- 0 to funcsProductNum-1) if(random*10 <= 5){funcsProduct += 1} else{funcsProduct += 0}
      for(i <- 0 to channelProductNum-1) if(random*10 <= 5){channelProduct += 1} else{channelProduct += 0}

      //println(designProduct)
      //println(funcsProduct)
      //println(channelProduct)
    }
}
