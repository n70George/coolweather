package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	
	public synchronized static boolean handleProvinceReponse(CoolWeatherDB coolWeatherDB,
			String response){
		
		if(!TextUtils.isEmpty(response)){
			String [] allProvinces=response.split(",");
			if(allProvinces!=null && allProvinces.length>0){
				for(String p:allProvinces){
					String [] array=p.split("\\|");
					Province province=new Province();
					province.setCode(array[0]);
					province.setName(array[1]);
					
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
		
	}
	
	
	public synchronized static boolean handleCityReponse(CoolWeatherDB coolWeatherDB,
			String response,int provinceId){
		
		if(!TextUtils.isEmpty(response)){
			String [] allCities=response.split(",");
			if(allCities!=null && allCities.length>0){
				for(String p:allCities){
					String [] array=p.split("\\|");
					City city=new City();
					city.setCode(array[0]);
					city.setName(array[1]);
					city.setProvinceId(provinceId);
					
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
		
	}
	
	
	public synchronized static boolean handleCountyReponse(CoolWeatherDB coolWeatherDB,
			String response,int cityId){
		
		if(!TextUtils.isEmpty(response)){
			String [] allCounties=response.split(",");
			if(allCounties!=null && allCounties.length>0){
				for(String p:allCounties){
					String [] array=p.split("\\|");
					County county=new County();
					county.setCode(array[0]);
					county.setName(array[1]);
					county.setCityId(cityId);
					
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
		
	}

}
