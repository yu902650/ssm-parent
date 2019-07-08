
package com.example.wechar.domain.entity.wechar;

import java.util.List;

public class NavigationMenu {
	
	private List<NavigationButton> button;  
	private NavigationCondition matchrule;
	  
    public List<NavigationButton> getButton() {  
        return button;  
    }  
  
    public void setButton(List<NavigationButton> button) {   
        this.button = button;  
    }

	public NavigationCondition getMatchrule() {
		return matchrule;
		
	}

	public void setMatchrule(NavigationCondition matchrule) {
		this.matchrule = matchrule;
		
	}  
}

