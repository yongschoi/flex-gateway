package yongs.temp.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Employee {
    String id;
    String name;
    long   salary;
    String project;
    String skill;
    String level;
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
}
