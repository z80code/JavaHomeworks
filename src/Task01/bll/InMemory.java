package Task01.bll;

import Task01.model.Member;

import java.util.ArrayList;
import java.util.List;

public class InMemory extends abstractBase

{
    private List<Member> members = new ArrayList<>();

    public InMemory(){
    };

    @Override
    protected List<Member> read() {
        return this.members;
    }

    @Override
    protected void save(List<Member> members) {
        this.members = members;
    }
}
