package com.harambe.devfetch.NetworkPojos;

import java.util.List;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class DevRantResponse {
    boolean success;
    List<Rants> rants;

    public boolean isSuccess() {
        return success;
    }

    public List<Rants> getRants() {
        return rants;
    }
}
