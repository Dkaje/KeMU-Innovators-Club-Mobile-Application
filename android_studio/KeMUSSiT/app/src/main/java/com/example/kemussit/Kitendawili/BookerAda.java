package com.example.kemussit.Kitendawili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.kemussit.R;

import java.util.ArrayList;

public class BookerAda extends ArrayAdapter<BookerMode> {
    public ArrayList<BookerMode> MainList;
    public ArrayList<BookerMode> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public BookerAda(Context context, int id, ArrayList<BookerMode> subjectArrayList) {

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

            convertView = vi.inflate(R.layout.bubble, null);

            holder = new ViewHolder();
            holder.txtmpes = convertView.findViewById(R.id.mainVenue);
            holder.count = convertView.findViewById(R.id.numbered);
            holder.indexed = convertView.findViewById(R.id.appr);
            holder.timely = convertView.findViewById(R.id.rej);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BookerMode subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.count.setText(pos + ".");
        pos++;
        holder.txtmpes.setText(subject.getVenue());
        holder.indexed.setText(subject.getCounter());
        holder.timely.setText(subject.getCount_2());
        return convertView;
    }

    public class ViewHolder {
        TextView txtmpes, count, indexed, timely;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<BookerMode> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    BookerMode subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<BookerMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
