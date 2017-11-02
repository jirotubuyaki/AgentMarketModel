package com.github.jirotubuyaki.main.scala.Market

import com.github.jirotubuyaki.main.scala.Market._
import com.github.jirotubuyaki.main.scala.Company._
import com.github.jirotubuyaki.main.scala.Product._
import com.github.jirotubuyaki.main.scala.Customer._
import com.github.jirotubuyaki.main.scala.Need._
import java.io._
import scala.io._
import scala.math._
import scala.collection.mutable._

class Market{
  val customerNum = 30000
  val companyNum = 15

  val designNum: Int = 10
  val funcsNum: Int = 10
  val channelNum: Int = 3

  var designCost: ListBuffer[Int] = ListBuffer[Int]()
  var funcsCost: ListBuffer[Int] = ListBuffer[Int]()
  var channelCost: ListBuffer[Int] = ListBuffer[Int]()

  var customer_objs: List[Customer] = List[Customer]()
  var company_objs: List[Company] = List[Company]()


  for(i <- 0 to customerNum - 1){
    var customer_obj: Customer = new Customer()
    customer_obj.Init(this, i)
    customer_objs = customer_objs :+ customer_obj
  }
  for(i <- 0 to designNum - 1) {designCost += (random * 10).toInt}
  for(i <- 0 to funcsNum - 1) {funcsCost += (random * 10).toInt}
  for(i <- 0 to channelNum - 1) {channelCost += (random * 10).toInt}

  for(i <- 0 to companyNum - 1){
    var company_obj: Company = new Company()
    company_obj.Init(this, i)
    company_objs = company_objs :+ company_obj
  }
  for(i <- 0 to companyNum - 1){
    company_objs(i).Product()
    company_objs(i).Advertise()
  }
  for(i <- 0 to customerNum - 1){
    customer_objs(i).Buy()
  }
  for(i <- 0 to companyNum - 1){
    println("CompanyID : "+ company_objs(i).id + " : " + company_objs(i).profit + " : CustomerNumber:" + company_objs(i).historyOfSell.length + "\n")
    //println("buyerId:")
    for(j <-0 to company_objs(i).historyOfSell.length-1){
      //println(company_objs(i).historyOfSell(j) + " ")
    }
  }
}
