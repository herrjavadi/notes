import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import mehdi.android.com.mynotenew.MyNotes;
import mehdi.android.com.mynotenew.R;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EXIST_NOTE = 0;
    private static final int CLEAR_NOTE = 1;
    private onNoteCallBack onNoteCallBack;


    List<MyNotes> myNotesList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == EXIST_NOTE) {
            View view = layoutInflater.inflate(R.layout.item_note, parent, false);

            return new NoteViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.layout_emty, parent, false);

            return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NoteViewHolder) {
            ((NoteViewHolder) holder).onBindNOteVIewHolder(myNotesList.get(position));
        } else if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).btnNewNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNoteCallBack.onBtnNewClick();
                }
            });
            ((EmptyViewHolder) holder).setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return myNotesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position = myNotesList.size() > 0 ? EXIST_NOTE : CLEAR_NOTE;
    }

    private class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView des;
        private ImageView edit;
        private ImageView delete;

        private NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_title);
            des = itemView.findViewById(R.id.tv_item_writeTitle);
            delete = itemView.findViewById(R.id.iv_item_delete);
            edit = itemView.findViewById(R.id.iv_item_edit);
        }

        private void onBindNOteVIewHolder(final MyNotes myNotes) {
            title.setText(myNotes.getTitle());
            des.setText(myNotes.getDescription());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNoteCallBack.onDeleteClick(myNotes);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNoteCallBack.onEditClick(myNotes);
                }
            });

        }
    }


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        private Button btnNewNote;

        private EmptyViewHolder(View itemView) {
            super(itemView);
            btnNewNote.findViewById(R.id.btn_empty_newNote);
        }

        public void setVisibility(int position) {
            btnNewNote.setVisibility(position);
        }

    }

    public interface onNoteCallBack {
        void onBtnNewClick();

        void onDeleteClick(MyNotes myNotes);

        void onEditClick(MyNotes myNotes);
    }
}

