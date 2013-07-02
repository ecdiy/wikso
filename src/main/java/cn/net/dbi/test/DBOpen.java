package cn.net.dbi.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBOpen {

	@Autowired
	DataSource dataSource;

	public void exec(String sql, Object... p) throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < p.length; i++) {
				ps.setObject(i + 1, p[i]);
			}
			ps.execute();
		}
	}

	public <E> List<E> getList(Class<E> cls, String sql, Object... param)
			throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			try {
				String query = (!sql.toLowerCase().startsWith("select") ? ("select * from "
						+ cls.getSimpleName() + (sql != null ? " " + sql : ""))
						: sql);
				PreparedStatement ps = conn.prepareStatement(query);
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i + 1, param[i]);
				}
				// System.out.println(query);
				ResultSet rs = ps.executeQuery();
				Field fs[] = cls.getDeclaredFields();
				LinkedList list = new LinkedList();
				while (rs.next()) {
					if (cls == String.class) {
						list.addLast((E) rs.getString(1));
					} else if (cls == int.class) {
						list.addLast(rs.getInt(1));
					} else if (cls == HashMap.class) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						ResultSetMetaData rsd = rs.getMetaData();
						for (int i = 1; i <= rsd.getColumnCount(); i++) {
							map.put(rsd.getColumnName(i),
									rs.getObject(rsd.getColumnName(i)));
						}
						list.addLast((E) map);
					} else {
						E ele = cls.newInstance();
						for (Field f : fs) {
							if (f.getType() == String.class) {
								f.set(ele, rs.getString(f.getName()));
							}
							if (f.getType() == boolean.class) {
								f.set(ele, rs.getBoolean(f.getName()));
							}
							if (f.getType() == int.class) {
								f.set(ele, rs.getInt(f.getName()));
							}
							if (f.getType() == long.class) {
								f.set(ele, rs.getLong(f.getName()));
							}
							if (f.getType() == int.class) {
								f.set(ele, rs.getInt(f.getName()));
							}
							// System.out.println("name = " +
							// rs.getString(f.getName()));
						}
						list.addLast(ele);
					}
				}
				rs.close();
				ps.close();
				if (list.size() > 0)
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public <E> E getObject(Class<E> cls, String sql, String... p)
			throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			List<E> list = getList(cls, sql, p);
			if (list != null)
				return list.get(0);
			return null;
		}
	}

	public HashMap<String, String> getParam(String sql, String... p)
			throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				for (int i = 0; i < p.length; i++) {
					ps.setObject(i + 1, p[i]);
				}
				HashMap<String, String> map = new HashMap<String, String>();
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						map.put(rs.getString("name"), rs.getString("val"));
					}
					return map;
				}
			}
		}
	}

	public List<HashMap<String, String>> getTblColumns(String tbl)
			throws SQLException {
		List<HashMap<String, String>> list = new LinkedList<>();
		try (Connection conn = dataSource.getConnection()) {
			ResultSet rsc = conn.getMetaData().getColumns("", "", tbl, "");
			int cc = rsc.getMetaData().getColumnCount();
			while (rsc.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 1; i <= cc; i++) {
					map.put("name", rsc.getString("COLUMN_NAME"));
					map.put("type", rsc.getString("TYPE_NAME"));
					map.put("size", rsc.getString("COLUMN_SIZE"));
				}
				list.add(map);
			}
		}
		return list;
	}

	public void getColumns() throws SQLException {
		try (Connection conn = dataSource.getConnection()) {

			ResultSet rs = conn.getMetaData().getColumns("", "",
					"FactorRelation", "");

			int cc = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= cc; i++) {
				System.out.print(rs.getMetaData().getColumnName(i) + "\t");
			}
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= cc; i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}

		}
	}

}
