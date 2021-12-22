package com.example.android_todo_first;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodosListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private Repository repository ;
    private List<Todo> todos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_todos_list, container, false);
        FloatingActionButton fabButton = view.findViewById(R.id.fab_button);
        recyclerView = view.findViewById(R.id.todos_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton fabButton = view.findViewById(R.id.fab_button);
        super.onViewCreated(view, savedInstanceState);
        repository = Repository.getInstance(getActivity().getApplicationContext());

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoFragment fragment = AddTodoFragment.newInstance();
                FragmentManager fm  = getParentFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

            }
        });
        todos = repository.getAllTodos();
        adapter = new TodoAdapter(todos);
        recyclerView.setAdapter(adapter);
    }
    }