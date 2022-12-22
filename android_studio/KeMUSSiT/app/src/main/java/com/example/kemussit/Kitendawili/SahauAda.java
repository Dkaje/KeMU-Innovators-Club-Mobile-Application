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

public class SahauAda extends ArrayAdapter<SahauMode> {
    public ArrayList<SahauMode> MainList;
    public ArrayList<SahauMode> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public SahauAda(Context context, int id, ArrayList<SahauMode> subjectArrayList) {

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

            convertView = vi.inflate(R.layout.witness, null);

            holder = new ViewHolder();
            holder.txtmpes = convertView.findViewById(R.id.mainVenue);
            holder.indexed = convertView.findViewById(R.id.registered);
            holder.count = convertView.findViewById(R.id.numbered);
            holder.club = convertView.findViewById(R.id.clubs);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SahauMode subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.count.setText(pos + ".");
        pos++;
        holder.txtmpes.setText(subject.getVenue());
        holder.indexed.setText(subject.getCounter());
        holder.club.setText(subject.getClub());
        return convertView;
    }

    public class ViewHolder {
        TextView txtmpes, count, indexed, club;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<SahauMode> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    SahauMode subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<SahauMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
