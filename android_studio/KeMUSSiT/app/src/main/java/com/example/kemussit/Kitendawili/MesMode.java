package com.example.kemussit.Kitendawili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.kemussit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MesMode extends ArrayAdapter<AdaMess> {
    public ArrayList<AdaMess> MainList;
    public ArrayList<AdaMess> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public MesMode(Context context, int id, ArrayList<AdaMess> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {
        if (subjectDataFilter == null) {
            subjectDataFilter = new SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.revenge, null);
            holder = new ViewHolder();
            holder.named = convertView.findViewById(R.id.TxtMess);
            holder.circleImageView = convertView.findViewById(R.id.Count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AdaMess subject = SubjectListTemp.get(position);
        String dater = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String summer;
        if (subject.getDate().equals(dater)) {
            summer = subject.getTime();
        } else {
            summer = subject.getDate() + " " + subject.getTime();
        }
        if (subject.getMove().equals("M")) {
            holder.named.setText(subject.getMessage() + "\ndelivered " + summer);
            holder.circleImageView.setImageResource(R.drawable.submitted);
        } else {
            holder.named.setText(subject.getMessage() + "\n" + summer);
            holder.circleImageView.setImageResource(R.drawable.reply);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView named;
        CircleImageView circleImageView;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<AdaMess> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    AdaMess subject = MainList.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            SubjectListTemp = (ArrayList<AdaMess>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}

