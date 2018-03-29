package com.multipz.onveggy.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.multipz.onveggy.Model.VegModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Admin on 25-08-2017.
 */

public class ManageCart {

    static Type founderListType;

    public static String prefCart = "localCart";

    public static boolean addNewProduct(VegModel item){

        ArrayList<VegModel> cartData = getCartList();
        for (int i = 0; i < cartData.size(); i++) {
            if (cartData.get(i).getName().contentEquals(item.getName())) {
                cartData.remove(i);
                break;
            }
        }
        cartData.add(item);

        Application.shared.putString(ManageCart.prefCart, new Gson().toJson(cartData));

        return true;

    }

    public static boolean removeProduct(VegModel item){
        ArrayList<VegModel> cartData = getCartList();

        if (cartData.size() > 0) {
            for (int i = 0; i < cartData.size(); i++) {
                if (cartData.get(i).getName().contentEquals(item.getName())) {
                    cartData.remove(i);
                    break;
                }
            }

            Application.shared.putString(ManageCart.prefCart, new Gson().toJson(cartData));

            return true;
        }else{
            return false;
        }
    }

    public static int getNoFitem(){
        return getCartList().size();
    }

    public static ArrayList<VegModel> getCartList(){
        ArrayList<VegModel> arryCart = new Gson().fromJson(Application.shared.getString(ManageCart.prefCart,""), getFounderListType());
        if (arryCart != null){
            return arryCart;
        }else{
            return new ArrayList<>();
        }
    }

    private static Type getFounderListType(){

        if (founderListType != null)
            return founderListType;

        return new TypeToken<ArrayList<VegModel>>(){}.getType();
    }

    public static VegModel isAvailable(VegModel item) {
        ArrayList<VegModel> cartData = getCartList();

        for (int i = 0; i < cartData.size(); i++) {
            if (cartData.get(i).getName().contentEquals(item.getName())) {
                item.setCartItem(cartData.get(i).getCartItem());
                return item;
            }
        }
        return item;
    }

    public static boolean clearCart(){
        Application.shared.putString(ManageCart.prefCart, "");
        return true;
    }
}
