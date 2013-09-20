package com.g3net.tool;

import org.apache.log4j.Logger;

public class PageBean {

	protected int start;// 开始行号，从0开始
	protected int end;// 结束行号，开区间，end不包含在内 ；
	protected int perPage;// 每页多少行
	protected int total;// 总记录行
	protected int page;// 当前页
	protected int maxPage;// 最大的页数
	private static Logger log = Logger.getLogger(PageBean.class);


	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPage(int page) {
		if(page<1) page=1;
		this.page = page;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public int getNextPage() {
		
		int nextPage = page + 1;
		if (nextPage > this.maxPage) {
			nextPage = this.maxPage;
		}
		return nextPage;
	}

	public int getPrevPage() {
		
		int prevPage = page - 1;

		if (prevPage < 1)
			prevPage = 1;

		return prevPage;
	
	}

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * 从第0行开始
	 * @return
	 */
	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	/**
	 * 
	 * @param total
	 *            总共多少行
	 * @param page
	 *            当前页号，从1开始
	 * @param perPage
	 *            每页多少行，如果为0，表明只显示一页
	 */
	public void doPage(int total, int page, int perPage) {

		if (total == 0) {
			start = 0;
			end = 0;
			return;
		}
		if (total < perPage) {
			perPage = total;
			page = 1;
		}
		if (page <= 0)
			page = 1;
		if (perPage == 0) {
			perPage = total;
		}
		if (total % perPage == 0) {
			maxPage = total / perPage;
		} else {
			maxPage = total / perPage + 1;
		}

		if (page > maxPage)
			page = maxPage;
		this.page = page;
		this.perPage = perPage;
		this.total = total;
		start = perPage * (page - 1);// 从0开始
		end = start + perPage; // 开区间

		if (end > total) {
			end = total;
		}
	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PageBean pu = new PageBean();
		pu.doPage(10, 66, 2);
		log.info(pu.getStart() + " " + pu.getEnd() + " maxPage="
				+ pu.getMaxPage());
	}

}
