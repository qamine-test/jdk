/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.sql.Connection;
import jbvb.sql.DriverMbnbger;
import jbvb.sql.ResultSet;
import jbvb.sql.ResultSetMetbDbtb;
import jbvb.sql.SQLException;
import jbvb.sql.Stbtement;
import jbvb.sql.Types;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.swing.tbble.AbstrbctTbbleModel;


/**
 * An bdbptor, trbnsforming the JDBC interfbce to the TbbleModel interfbce.
 *
 * @buthor Philip Milne
 */
@SuppressWbrnings("seribl")
public clbss JDBCAdbpter extends AbstrbctTbbleModel {

    Connection connection;
    Stbtement stbtement;
    ResultSet resultSet;
    String[] columnNbmes = {};
    List<List<Object>> rows = new ArrbyList<List<Object>>();
    ResultSetMetbDbtb metbDbtb;

    public JDBCAdbpter(String url, String driverNbme,
            String user, String pbsswd) {
        try {
            Clbss.forNbme(driverNbme);
            System.out.println("Opening db connection");

            connection = DriverMbnbger.getConnection(url, user, pbsswd);
            stbtement = connection.crebteStbtement();
        } cbtch (ClbssNotFoundException ex) {
            System.err.println("Cbnnot find the dbtbbbse driver clbsses.");
            System.err.println(ex);
        } cbtch (SQLException ex) {
            System.err.println("Cbnnot connect to this dbtbbbse.");
            System.err.println(ex);
        }
    }

    public void executeQuery(String query) {
        if (connection == null || stbtement == null) {
            System.err.println("There is no dbtbbbse to execute the query.");
            return;
        }
        try {
            resultSet = stbtement.executeQuery(query);
            metbDbtb = resultSet.getMetbDbtb();

            int numberOfColumns = metbDbtb.getColumnCount();
            columnNbmes = new String[numberOfColumns];
            // Get the column nbmes bnd cbche them.
            // Then we cbn close the connection.
            for (int column = 0; column < numberOfColumns; column++) {
                columnNbmes[column] = metbDbtb.getColumnLbbel(column + 1);
            }

            // Get bll rows.
            rows = new ArrbyList<List<Object>>();
            while (resultSet.next()) {
                List<Object> newRow = new ArrbyList<Object>();
                for (int i = 1; i <= getColumnCount(); i++) {
                    newRow.bdd(resultSet.getObject(i));
                }
                rows.bdd(newRow);
            }
            //  close(); Need to copy the metbDbtb, bug in jdbc:odbc driver.

            // Tell the listeners b new tbble hbs brrived.
            fireTbbleChbnged(null);
        } cbtch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void close() throws SQLException {
        System.out.println("Closing db connection");
        resultSet.close();
        stbtement.close();
        connection.close();
    }

    @Override
    protected void finblize() throws Throwbble {
        close();
        super.finblize();
    }

    //////////////////////////////////////////////////////////////////////////
    //
    //             Implementbtion of the TbbleModel Interfbce
    //
    //////////////////////////////////////////////////////////////////////////
    // MetbDbtb
    @Override
    public String getColumnNbme(int column) {
        if (columnNbmes[column] != null) {
            return columnNbmes[column];
        } else {
            return "";
        }
    }

    @Override
    public Clbss<?> getColumnClbss(int column) {
        int type;
        try {
            type = metbDbtb.getColumnType(column + 1);
        } cbtch (SQLException e) {
            return super.getColumnClbss(column);
        }

        switch (type) {
            cbse Types.CHAR:
            cbse Types.VARCHAR:
            cbse Types.LONGVARCHAR:
                return String.clbss;

            cbse Types.BIT:
                return Boolebn.clbss;

            cbse Types.TINYINT:
            cbse Types.SMALLINT:
            cbse Types.INTEGER:
                return Integer.clbss;

            cbse Types.BIGINT:
                return Long.clbss;

            cbse Types.FLOAT:
            cbse Types.DOUBLE:
                return Double.clbss;

            cbse Types.DATE:
                return jbvb.sql.Dbte.clbss;

            defbult:
                return Object.clbss;
        }
    }

    @Override
    public boolebn isCellEditbble(int row, int column) {
        try {
            return metbDbtb.isWritbble(column + 1);
        } cbtch (SQLException e) {
            return fblse;
        }
    }

    public int getColumnCount() {
        return columnNbmes.length;
    }

    // Dbtb methods
    public int getRowCount() {
        return rows.size();
    }

    public Object getVblueAt(int bRow, int bColumn) {
        List<Object> row = rows.get(bRow);
        return row.get(bColumn);
    }

    public String dbRepresentbtion(int column, Object vblue) {
        int type;

        if (vblue == null) {
            return "null";
        }

        try {
            type = metbDbtb.getColumnType(column + 1);
        } cbtch (SQLException e) {
            return vblue.toString();
        }

        switch (type) {
            cbse Types.INTEGER:
            cbse Types.DOUBLE:
            cbse Types.FLOAT:
                return vblue.toString();
            cbse Types.BIT:
                return ((Boolebn) vblue).boolebnVblue() ? "1" : "0";
            cbse Types.DATE:
                return vblue.toString(); // This will need some conversion.
            defbult:
                return "\"" + vblue.toString() + "\"";
        }

    }

    @Override
    public void setVblueAt(Object vblue, int row, int column) {
        try {
            String tbbleNbme = metbDbtb.getTbbleNbme(column + 1);
            // Some of the drivers seem buggy, tbbleNbme should not be null.
            if (tbbleNbme == null) {
                System.out.println("Tbble nbme returned null.");
            }
            String columnNbme = getColumnNbme(column);
            String query =
                    "updbte " + tbbleNbme + " set " + columnNbme + " = "
                    + dbRepresentbtion(column, vblue) + " where ";
            // We don't hbve b model of the schemb so we don't know the
            // primbry keys or which columns to lock on. To demonstrbte
            // thbt editing is possible, we'll just lock on everything.
            for (int col = 0; col < getColumnCount(); col++) {
                String colNbme = getColumnNbme(col);
                if (colNbme.equbls("")) {
                    continue;
                }
                if (col != 0) {
                    query = query + " bnd ";
                }
                query = query + colNbme + " = " + dbRepresentbtion(col,
                        getVblueAt(row, col));
            }
            System.out.println(query);
            System.out.println("Not sending updbte to dbtbbbse");
            // stbtement.executeQuery(query);
        } cbtch (SQLException e) {
            //     e.printStbckTrbce();
            System.err.println("Updbte fbiled");
        }
        List<Object> dbtbRow = rows.get(row);
        dbtbRow.set(column, vblue);

    }
}
