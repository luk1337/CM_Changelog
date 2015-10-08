package org.cyanogenmod.changelog.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.cyanogenmod.changelog.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChangelogAdapter extends ArrayAdapter<Change>{
    public ChangelogAdapter(Context context, ArrayList<Change> changes) {
        super(context, 0, changes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Change change = getItem(position);
        String commitDate = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview, parent, false);
        }

        TextView subject = (TextView) convertView.findViewById(R.id.subject);
        TextView project = (TextView) convertView.findViewById(R.id.project);
        TextView last_updated = (TextView) convertView.findViewById(R.id.last_updated);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                    java.util.Locale.getDefault());
            Date convertedCommitDate = sdf.parse(change.mLastUpdated);
            commitDate = sdf.format(convertedCommitDate );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        subject.setText(change.mSubject);
        last_updated.setText(commitDate);
        if (change.mProject.equals("android")) {
            project.setText(change.mProject + "_manifest");
        } else {
            project.setText(change.mProject.replace("android_", ""));
        }
        return convertView;
    }
}

