package com.gsatechworld.linnaas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.linnaas.utils.search.SearchRepository;
import com.gsatechworld.linnaas.utils.search.SearchResp;
import com.gsatechworld.linnaas.utils.search.SearchResult;

import javax.inject.Inject;

public class SearchModel extends ViewModel {

    private SearchRepository searchRepository;
    private LiveData<SearchResp> searchRespLiveData;
    @Inject
    public SearchModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public LiveData<SearchResp> getSearchResult(SearchResp searchResp){
        searchRespLiveData = new MutableLiveData<>();
        searchRespLiveData = searchRepository.getSearchResult(searchResp);
        return searchRespLiveData;
    }
}
