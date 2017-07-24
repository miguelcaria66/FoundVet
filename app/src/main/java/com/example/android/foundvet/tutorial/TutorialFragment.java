package com.example.android.foundvet.tutorial;

import android.content.Intent;import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.foundvet.R;
import com.example.android.foundvet.login.LoginActivity;
import com.example.android.foundvet.pets.MyPetsActivity;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link TutorialActivity} samples.</p>
 */

public class TutorialFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static TutorialFragment create(int pageNumber) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }


    /**
     * Method that fill tutorial areas with content.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_tutorial, container, false);

        // Implements Skip Button to LoginActivity
        Button btn = (Button) rootView.findViewById(R.id.button_tutorial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        DotIndicator indicator = (DotIndicator) rootView.findViewById(R.id.dots_tutorial);


        // Inserts images and texts of each step of the tutorial
        switch (getPageNumber()) {
            case 0:
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.mipmap.award_icon);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                indicator.setSelectedItem(0, true);
                break;
            case 1:
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.drawable.profile_ex);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                indicator.setSelectedItem(1, true);
                break;
            case 2:
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.drawable.profile_ex);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                indicator.setSelectedItem(2, true);
                break;
            case 3:
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.drawable.profile_ex);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                indicator.setSelectedItem(3, true);
                break;
            case 4:
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.drawable.profile_ex);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                indicator.setSelectedItem(4, true);
                break;
            default: //case 5
                ((TextView) rootView.findViewById(R.id.tv_titletutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_titletutorial)).setVisibility(View.VISIBLE);
                ((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setImageResource(R.drawable.profile_ex);
                //((ImageView) rootView.findViewById(R.id.iv_imagetutorial)).setVisibility(View.VISIBLE);
                ((TextView) rootView.findViewById(R.id.tv_texttutorial)).setText(getString(R.string.tut0));
                //((TextView) rootView.findViewById(R.id.tv_texttutorial)).setVisibility(View.VISIBLE);
                ((Button) rootView.findViewById(R.id.button_tutorial)).setText("START");
                indicator.setSelectedItem(5, true);
                break;
        }
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
