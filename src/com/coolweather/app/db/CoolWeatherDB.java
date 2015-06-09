package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class CoolWeatherDB {
	
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME="Cool_Weather";
	
	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	/**
	 * ��ʼ��
	 * @param context
	 */
	private CoolWeatherDB(Context context){
		
		CoolWeatherOpenHelper dbHelper= new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
		
	}
	
	/**
	 * ʵ��������
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * ����ʡ����Ϣ
	 * @param province
	 */
	public void saveProvince(Province province)	{
		
		if(province!=null){
			ContentValues values= new ContentValues();
			values.put("province_name", province.getName());
			values.put("province_code", province.getCode());
			db.insert("Province", null, values);
		}
		
	}
	
	
	public List<Province> loadProvinces(){
		
		List<Province> result = new ArrayList<Province>();
		
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
			Province province=new Province();
			province.setId(cursor.getInt(cursor.getColumnIndex("id")));
			province.setName(cursor.getString(cursor.getColumnIndex("province_name")));
			province.setCode(cursor.getString(cursor.getColumnIndex("province_code")));
			result.add(province);
			} while(cursor.moveToNext());
		}
			
		if(cursor!=null) cursor.close();
		
		return result;
		
	}
	
	
	/**
	 * �����е���Ϣ
	 * @param province
	 */
	public void saveCity(City city)	{
		
		if(city!=null){
			ContentValues values= new ContentValues();
			values.put("city_name", city.getName());
			values.put("city_code", city.getCode());
			values.put("province_id",city.getProvinceId());
			db.insert("City", null, values);
		}
		
	}
	
	
	public List<City> loadCities(int provinceId){
		
		List<City> result = new ArrayList<City>();
		
		Cursor cursor=db.query("City", null, "province_id=?", new String[] {String.valueOf(provinceId)}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
			City city=new City();
			city.setId(cursor.getInt(cursor.getColumnIndex("id")));
			city.setName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
			
			result.add(city);
			} while(cursor.moveToNext());
		}
			
		if(cursor!=null) cursor.close();
		
		return result;
		
	}
	

	/**
	 * ����������Ϣ
	 * @param province
	 */
	public void saveCounty(County county)	{
		
		if(county!=null){
			ContentValues values= new ContentValues();
			values.put("county_name", county.getName());
			values.put("county_code", county.getCode());
			values.put("city_id", county.getCityId());
			
			db.insert("County", null, values);
		}
		
	}
	
	
public List<County> loadCountries(int cityId){
		
		List<County> result = new ArrayList<County>();
		
		Cursor cursor=db.query("County", null, "city_id=?", new String[] {String.valueOf(cityId)}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				County county=new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
			
			result.add(county);
			} while(cursor.moveToNext());
		}
			
		if(cursor!=null) cursor.close();
		
		return result;
		
	}
	
	
	
	

}
