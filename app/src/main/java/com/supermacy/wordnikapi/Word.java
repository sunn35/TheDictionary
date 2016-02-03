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

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.android.volley.Network;

/**
 * Created by sudhanshu on 25/1/16.
 */
public class Word {

    //public static final String API_KEY = "9f801cdaf4f29ca7bd88f034992178451b9fda98aa9b6190a";
    public static String API_KEY;

    //to be deleted just for testing
    private static Context context;

    /*    private static final int EXAMPLES = 1;
        private static final int DEFINITIONS = 2;
        private static final int TOP_EXAMPLE = 3;
        private static final int RELATED_WORDS = 4;
        private static final int PRONUNCIATION = 5;
        private static final int HYPHENATION = 6;
        private static final int FREQUENCY = 7;
        private static final int PHRASES = 8;
        private static final int ETYMOLOGIES = 9;
        private static final int AUDIO = 10;*/

    public Word(String API_KEY, Context context) {
        this.API_KEY = API_KEY;
        this.context = context;
    }

    public void examples(String word, boolean includeDuplicates, boolean useCanonical, int skip, int limit) {
        try {
            String EXAMPLE_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                EXAMPLE_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/examples?includeDuplicates=" + includeDuplicates + "&useCanonical=" + useCanonical + "&skip=" + skip + "&limit=" + limit + "&api_key=" + API_KEY;
                EXAMPLE_URL = URLEncoder.encode(EXAMPLE_URL, "UTF-8");

                RequestQueue mRequestQueue;

// Instantiate the cache
                Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
                mRequestQueue = new RequestQueue(cache, network);

// Start the queue
                mRequestQueue.start();

// Formulate the request and handle the response.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, EXAMPLE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });

// Add the request to the RequestQueue.
                mRequestQueue.add(stringRequest);
















/*                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, EXAMPLE_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray exampleJsonArray = jsonObject.optJSONArray("examples");
                            JSONObject examplesData;
                            String exampleDataToSave="";
                            for (int i = 0; i < exampleJsonArray.length(); i++) {
                                examplesData = exampleJsonArray.getJSONObject(i);
                                exampleDataToSave = (i + 1) + ". " + examplesData.optString("text") + "\n";
                            }
                            Log.d("EXAMPLES",exampleDataToSave);
                            //saveToDatabase(exampleDataToSave);
                        } catch (JSONException e) {
                            Log.d("JSON ERROR", "ERROR PARSING EXAMPLE JSON DATA");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING EXAMPLES");
                    }
                }

                );*/

            } else {
                EXAMPLE_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void word(String word, boolean useCanonical, boolean includeSuggestions) {

    }

    public void definitions(String word, int limit, String partOfSpeech, boolean includeRelated, String sourceDictionaries, boolean useCanonical, boolean includeTags) {
        try {
            String DEFINITIONS_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                DEFINITIONS_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/definitions?limit=" + limit + "&partOfSpeech=" + partOfSpeech + "&includeRelated=" + includeRelated + "&sourceDictionaries=" + sourceDictionaries + "&useCanonical=" + useCanonical + "&includeTags=" + includeTags + "&api_key=" + API_KEY;
                DEFINITIONS_URL = URLEncoder.encode(DEFINITIONS_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, DEFINITIONS_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING DEFINITIONS");
                    }
                });
            } else {
                DEFINITIONS_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void topExample(String word, boolean useCanonical) {
        try {
            String TOP_EXAMPLE_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                TOP_EXAMPLE_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/topExample?useCanonical=" + useCanonical + "&api_key=" + API_KEY;
                TOP_EXAMPLE_URL = URLEncoder.encode(TOP_EXAMPLE_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, TOP_EXAMPLE_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING TOP EXAMPLE");
                    }
                });

            } else {
                TOP_EXAMPLE_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void relatedWords(String word, boolean useCanonical, String relationshipTypes, int limitPerRelationshipType) {
        try {
            String RELATED_WORDS_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                RELATED_WORDS_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/relatedWords?useCanonical=" + useCanonical + "&relationshipTypes=" + relationshipTypes + "&limitPerRelationshipType=" + limitPerRelationshipType + "&api_key=" + API_KEY;
                RELATED_WORDS_URL = URLEncoder.encode(RELATED_WORDS_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, RELATED_WORDS_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING RELATED WORDS");
                    }
                });
            } else {
                RELATED_WORDS_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void pronunciations(String word, boolean useCanonical, String sourceDictionary, String typeFormat, int limit) {
        try {
            String PRONUNCIATION_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                PRONUNCIATION_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/pronunciations?useCanonical=" + useCanonical + "&sourceDictionary=" + sourceDictionary + "&typeFormat=" + typeFormat + "&limit=" + limit + "&api_key=" + API_KEY;
                PRONUNCIATION_URL = URLEncoder.encode(PRONUNCIATION_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, PRONUNCIATION_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING PRONUNCIATION");
                    }
                });
            } else {
                PRONUNCIATION_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void hyphenation(String word, boolean useCanonical, String sourceDictionary, int limit) {
        try {
            String HYPHENATION_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                HYPHENATION_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/hyphenation?useCanonical=" + useCanonical + "&sourceDictionary=" + sourceDictionary + "&limit=" + limit + "&api_key=" + API_KEY;
                HYPHENATION_URL = URLEncoder.encode(HYPHENATION_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, HYPHENATION_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING HYPHENATION");
                    }
                });

            } else {
                HYPHENATION_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void frequency(String word, boolean useCanonical, int startYear, int endYear) {
        try {
            String FREQUENCY_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                FREQUENCY_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/frequency?useCanonical=" + useCanonical + "&startYear=" + startYear + "&endYear=" + endYear + "&api_key=" + API_KEY;
                FREQUENCY_URL = URLEncoder.encode(FREQUENCY_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, FREQUENCY_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING FREQUENCY");
                    }
                });

            } else {
                FREQUENCY_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void phrases(String word, int limit, int wlmi, boolean useCanonical) {
        try {
            String PHRASES_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                PHRASES_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/phrases?limit=" + limit + "&wlmi=" + wlmi + "&useCanonical=" + useCanonical + "&api_key=" + API_KEY;
                PHRASES_URL = URLEncoder.encode(PHRASES_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, PHRASES_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING PHRASES");
                    }
                });

            } else {
                PHRASES_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void etymologies(String word, boolean useCanonical) {
        try {
            String ETYMOLOGIES_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                ETYMOLOGIES_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/etymologies?useCanonical=" + useCanonical + "&api_key=" + API_KEY;
                ETYMOLOGIES_URL = URLEncoder.encode(ETYMOLOGIES_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, ETYMOLOGIES_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING ETYMOLOGIES");
                    }
                });

            } else {
                ETYMOLOGIES_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void audio(String word, boolean useCanonical, int limit) {
        try {
            String AUDIO_URL;
            String wordConj = word;
            if (wordConj.replaceAll(" ", "") != null) {
                AUDIO_URL = "http://api.wordnik.com:80/v4/word.json/" + word + "/audio?useCanonical=" + useCanonical + "&limit=" + limit + "&api_key=" + API_KEY;
                AUDIO_URL = URLEncoder.encode(AUDIO_URL, "UTF-8");

                JsonObjectRequest exampleObjectRequest = new JsonObjectRequest(Request.Method.GET, AUDIO_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERROR", "VOLLEY ERROR IN GETTING AUDIO");
                    }
                });
            } else {
                AUDIO_URL = "WORD CANNOT BE EMPTY";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
