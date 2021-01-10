/*
package com.example.lab4;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import static com.example.lab4.MainActivity.mainActivityapplicationContext;
import static com.example.lab4.MainActivity.orderRecyclerView;

public class PrintOut {

    public PrintOut() {
    }

    OrderAdapter adapter;

    public void orderNrText(String orderNr, OrderInfo orderInfo) {
        orderInfo.setOrderNr(orderNr);
    }

    public void fromText(String from, OrderInfo orderInfo) {
        orderInfo.setFrom(from);
    }

    public void toText(String to, OrderInfo orderInfo) {
        orderInfo.setTo(to);
    }

    public void dateText(String date, OrderInfo orderInfo) {
        orderInfo.setDate(date);
    }

    public void weightText(float weight, OrderInfo orderInfo) {
        orderInfo.setWeight(weight);
    }

    public void isGlassText(boolean isGlass, OrderInfo orderInfo) {
        orderInfo.setGlass(isGlass);
    }

    public void addToOrderList(List<OrderInfo> orderList, OrderInfo orderInfo) {

        orderList.add(orderInfo);
    }

    public void setAdapter(List<OrderInfo> orderInfoList) {
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivityapplicationContext));
        adapter = new OrderAdapter(orderInfoList);
        orderRecyclerView.setAdapter(adapter);
    }
}
*/
