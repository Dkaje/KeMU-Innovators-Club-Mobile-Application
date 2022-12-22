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

public class SummMode extends ArrayAdapter<Keeper> {
    public ArrayList<Keeper> MainList;
    public ArrayList<Keeper> SubjectListTemp;
    public MessageDataFilter messageDataFilter;

    public SummMode(Context context, int id, ArrayList<Keeper> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {

        if (messageDataFilter == null) {

            messageDataFilter = new MessageDataFilter();
        }
        return messageDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.revenge, null);
            holder = new ViewHolder();
            holder.named = convertView.findViewById(R.id.TxtMess);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Keeper subject = SubjectListTemp.get(position);
        String dater = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String summer;
        if (subject.getDate().equals(dater)) {
            summer = subject.getTime();
        } else {
            summer = subject.getDate() + " " + subject.getTime();
        }
        holder.named.setText(subject.getName() + ", student\n" + subject.getMessage() + "\ndelivered " + summer);

        return convertView;

    }

    public class ViewHolder {
        TextView named;
    }

    private class MessageDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<Keeper> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    Keeper subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<Keeper>) filterResults.values;
            notifyDataSetChanged();
            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
