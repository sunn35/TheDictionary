package com.supermacy.wordnikapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.android.volley.Network;

/**
 * Created by sudhanshu on 25/1/16.
 */
public class Word {

    //public static final String API_KEY = "9f801cdaf4f29ca7bd88f034992178451b9fda98aa9b6190a";
    public static String API_KEY;
    public static Cache cache;
    public static Network network;
    public static RequestQueue mRequestQueue;
    private ArrayList<String> EXAMPLES_PARSED = new ArrayList<String>();


//    public static ArrayList<String> UrlList;


    public Word(String API_KEY, RequestQueue mRequestQueue, Cache cache, Network network) {
        this.API_KEY = API_KEY;
        this.mRequestQueue = mRequestQueue;
        this.cache = cache;
        this.network = network;
//        UrlList = new ArrayList<String>();
    }

    public void getExamples(String word, boolean includeDuplicates, boolean useCanonical, int skip, int limit) {
        try {
            final String EXAMPLE_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                EXAMPLE_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/examples?includeDuplicates=" + includeDuplicates + "&useCanonical=" + useCanonical + "&skip=" + skip + "&limit=" + limit + "&api_key=" + API_KEY;

//                UrlList.add(0, EXAMPLE_URL);

/*
                RequestQueue mRequestQueue;
                Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap
                Network network = new BasicNetwork(new HurlStack());
                mRequestQueue = new RequestQueue(cache, network);
                mRequestQueue.start();
*/
                StringRequest stringRequest = new StringRequest(Request.Method.GET, EXAMPLE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    // Do something with the response
                                    JSONObject examplesObject = new JSONObject(response);
                                    JSONArray examplesArray = examplesObject.optJSONArray("examples");
                                    for (int i = 0; i < examplesArray.length(); i++) {
                                        String example = examplesArray.getJSONObject(i).optString("text");
                                        EXAMPLES_PARSED.add(i, example);
                                    }
                                    Log.d("response", response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
            } else {
                EXAMPLE_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void word(String word, boolean useCanonical, boolean includeSuggestions) {

    }

    public void getDefinitions(String word, int limit, String partOfSpeech, boolean includeRelated, String sourceDictionaries, boolean useCanonical, boolean includeTags) {
        try {
            String DEFINITIONS_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                DEFINITIONS_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/definitions?limit=" + limit + "&partOfSpeech=" + partOfSpeech + "&includeRelated=" + includeRelated + "&sourceDictionaries=" + sourceDictionaries + "&useCanonical=" + useCanonical + "&includeTags=" + includeTags + "&api_key=" + API_KEY;
                //              UrlList.add(1, DEFINITIONS_URL);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, DEFINITIONS_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
            } else {
                DEFINITIONS_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getTopExample(String word, boolean useCanonical) {
        try {
            String TOP_EXAMPLE_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                TOP_EXAMPLE_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/topExample?useCanonical=" + useCanonical + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, TOP_EXAMPLE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //            UrlList.add(2, TOP_EXAMPLE_URL);
            } else {
                TOP_EXAMPLE_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getRelatedWords(String word, boolean useCanonical, String relationshipTypes, int limitPerRelationshipType) {
        try {
            String RELATED_WORDS_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                RELATED_WORDS_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/relatedWords?useCanonical=" + useCanonical + "&relationshipTypes=" + relationshipTypes + "&limitPerRelationshipType=" + limitPerRelationshipType + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, RELATED_WORDS_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //          UrlList.add(3, RELATED_WORDS_URL);
            } else {
                RELATED_WORDS_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getPronunciation(String word, boolean useCanonical, String sourceDictionary, String typeFormat, int limit) {
        try {
            String PRONUNCIATION_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                PRONUNCIATION_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/pronunciations?useCanonical=" + useCanonical + "&sourceDictionary=" + sourceDictionary + "&typeFormat=" + typeFormat + "&limit=" + limit + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, PRONUNCIATION_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //            UrlList.add(4, PRONUNCIATION_URL);
            } else {
                PRONUNCIATION_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getHyphenation(String word, boolean useCanonical, String sourceDictionary, int limit) {
        try {
            String HYPHENATION_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                HYPHENATION_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/hyphenation?useCanonical=" + useCanonical + "&sourceDictionary=" + sourceDictionary + "&limit=" + limit + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, HYPHENATION_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //            UrlList.add(5, HYPHENATION_URL);
            } else {
                HYPHENATION_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getFrequency(String word, boolean useCanonical, int startYear, int endYear) {
        try {
            String FREQUENCY_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                FREQUENCY_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/frequency?useCanonical=" + useCanonical + "&startYear=" + startYear + "&endYear=" + endYear + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, FREQUENCY_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //            UrlList.add(6, FREQUENCY_URL);
            } else {
                FREQUENCY_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getPhrases(String word, int limit, int wlmi, boolean useCanonical) {
        try {
            String PHRASES_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                PHRASES_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/phrases?limit=" + limit + "&wlmi=" + wlmi + "&useCanonical=" + useCanonical + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, PHRASES_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //            UrlList.add(7, PHRASES_URL);
            } else {
                PHRASES_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getEtymology(String word, boolean useCanonical) {
        try {
            String ETYMOLOGIES_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                ETYMOLOGIES_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/etymologies?useCanonical=" + useCanonical + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, ETYMOLOGIES_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
//                UrlList.add(8, ETYMOLOGIES_URL);
            } else {
                ETYMOLOGIES_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getAudioUrl(String word, boolean useCanonical, int limit) {
        try {
            String AUDIO_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                word = URLEncoder.encode(word, "UTF-8");
                AUDIO_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/audio?useCanonical=" + useCanonical + "&limit=" + limit + "&api_key=" + API_KEY;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, AUDIO_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Log.d("response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                                Log.d("error", error.toString());
                            }
                        });
                mRequestQueue.add(stringRequest);
                //              UrlList.add(9, AUDIO_URL);
            } else {
                AUDIO_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
