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

public class WatuAda extends ArrayAdapter<PayMode> {
    public ArrayList<PayMode> MainList;
    public ArrayList<PayMode> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public WatuAda(Context context, int id, ArrayList<PayMode> subjectArrayList) {

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

            convertView = vi.inflate(R.layout.roller, null);

            holder = new ViewHolder();
            holder.txtmpes = convertView.findViewById(R.id.myCate);
            holder.paid = convertView.findViewById(R.id.myPay);
            holder.txtamt = convertView.findViewById(R.id.myFe);
            holder.count = convertView.findViewById(R.id.txtOff);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PayMode subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.count.setText(pos + ".");
        pos++;
        String form;
        if (subject.getDeter().equals("F")){
            form="FRESH_CARD";
        }else if (subject.getDeter().equals("U")){
            form="UPGRADING";
        }else{
            form="RENEWAL";
        }
        holder.txtmpes.setText(form);
        holder.paid.setText(subject.getReg_no());
        holder.txtamt.setText("Ksh. " + subject.getFees());
        return convertView;
    }

    public class ViewHolder {
        TextView txtmpes, paid, txtamt, count;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<PayMode> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    PayMode subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<PayMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
