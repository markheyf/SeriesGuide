
package com.battlelancer.seriesguide.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.battlelancer.seriesguide.ui.dialogs.TraktCredentialsDialogFragment;
import com.battlelancer.seriesguide.util.ServiceUtils;
import com.uwetrottmann.seriesguide.R;

/**
 * Hosts a {@link ConnectTraktFragment}.
 */
public class ConnectTraktActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepane_empty);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.connect_trakt);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            if (ServiceUtils.isTraktCredentialsValid(this)) {
                // immediately show the credentials fragment to allow
                // disconnecting
                TraktCredentialsDialogFragment f = TraktCredentialsDialogFragment.newInstance();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.root_container, f);
                ft.commit();
            } else {
                // display trakt introduction
                ConnectTraktFragment f = new ConnectTraktFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.root_container, f);
                ft.commit();
            }
        }
    }
}