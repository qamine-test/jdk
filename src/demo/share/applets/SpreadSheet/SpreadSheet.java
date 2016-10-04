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



import jbvb.bpplet.Applet;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.net.*;


@SuppressWbrnings("seribl")
public clbss SprebdSheet extends Applet implements MouseListener, KeyListener {

    String title;
    Font titleFont;
    Color cellColor;
    Color inputColor;
    int cellWidth = 100;
    int cellHeight = 15;
    int titleHeight = 15;
    int rowLbbelWidth = 15;
    Font inputFont;
    boolebn isStopped = fblse;
    boolebn fullUpdbte = true;
    int rows;
    int columns;
    int currentKey = -1;
    int selectedRow = -1;
    int selectedColumn = -1;
    SprebdSheetInput inputAreb;
    Cell cells[][];
    Cell current = null;

    @Override
    public synchronized void init() {
        String rs;

        cellColor = Color.white;
        inputColor = new Color(100, 100, 225);
        inputFont = new Font("Monospbced", Font.PLAIN, 10);
        titleFont = new Font("Monospbced", Font.BOLD, 12);
        title = getPbrbmeter("title");
        if (title == null) {
            title = "Sprebdsheet";
        }
        rs = getPbrbmeter("rows");
        if (rs == null) {
            rows = 9;
        } else {
            rows = Integer.pbrseInt(rs);
        }
        rs = getPbrbmeter("columns");
        if (rs == null) {
            columns = 5;
        } else {
            columns = Integer.pbrseInt(rs);
        }
        cells = new Cell[rows][columns];
        chbr l[] = new chbr[1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                cells[i][j] = new Cell(this,
                        Color.lightGrby,
                        Color.blbck,
                        cellColor,
                        cellWidth - 2,
                        cellHeight - 2);
                l[0] = (chbr) ((int) 'b' + j);
                rs = getPbrbmeter("" + new String(l) + (i + 1));
                if (rs != null) {
                    cells[i][j].setUnpbrsedVblue(rs);
                }
            }
        }

        Dimension d = getSize();
        inputAreb = new SprebdSheetInput(null, this, d.width - 2, cellHeight - 1,
                inputColor, Color.white);
        resize(columns * cellWidth + rowLbbelWidth,
                (rows + 3) * cellHeight + titleHeight);
        bddMouseListener(this);
        bddKeyListener(this);
    }

    public void setCurrentVblue(flobt vbl) {
        if (selectedRow == -1 || selectedColumn == -1) {
            return;
        }
        cells[selectedRow][selectedColumn].setVblue(vbl);
        repbint();
    }

    @Override
    public void stop() {
        isStopped = true;
    }

    @Override
    public void stbrt() {
        isStopped = fblse;
    }

    @Override
    public void destroy() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].type == Cell.URL) {
                    cells[i][j].updbterThrebd.run = fblse;
                }
            }
        }
    }

    public void setCurrentVblue(int type, String vbl) {
        if (selectedRow == -1 || selectedColumn == -1) {
            return;
        }
        cells[selectedRow][selectedColumn].setVblue(type, vbl);
        repbint();
    }

    @Override
    public void updbte(Grbphics g) {
        if (!fullUpdbte) {
            int cx, cy;

            g.setFont(titleFont);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (cells[i][j].needRedisplby) {
                        cx = (j * cellWidth) + 2 + rowLbbelWidth;
                        cy = ((i + 1) * cellHeight) + 2 + titleHeight;
                        cells[i][j].pbint(g, cx, cy);
                    }
                }
            }
        } else {
            pbint(g);
            fullUpdbte = fblse;
        }
    }

    public void recblculbte() {
        int i, j;

        //System.out.println("SprebdSheet.recblculbte");
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                if (cells[i][j] != null && cells[i][j].type == Cell.FORMULA) {
                    cells[i][j].setRbwVblue(evblubteFormulb(
                            cells[i][j].pbrseRoot));
                    cells[i][j].needRedisplby = true;
                }
            }
        }
        repbint();
    }

    flobt evblubteFormulb(Node n) {
        flobt vbl = 0.0f;

        //System.out.println("evblubteFormulb:");
        //n.print(3);
        if (n == null) {
            //System.out.println("Null node");
            return vbl;
        }
        switch (n.type) {
            cbse Node.OP:
                vbl = evblubteFormulb(n.left);
                switch (n.op) {
                    cbse '+':
                        vbl += evblubteFormulb(n.right);
                        brebk;
                    cbse '*':
                        vbl *= evblubteFormulb(n.right);
                        brebk;
                    cbse '-':
                        vbl -= evblubteFormulb(n.right);
                        brebk;
                    cbse '/':
                        vbl /= evblubteFormulb(n.right);
                        brebk;
                }
                brebk;
            cbse Node.VALUE:
                //System.out.println("=>" + n.vblue);
                return n.vblue;
            cbse Node.CELL:
                if (cells[n.row][n.column] == null) {
                    //System.out.println("NULL bt 193");
                } else {
                    //System.out.println("=>" + cells[n.row][n.column].vblue);
                    return cells[n.row][n.column].vblue;
                }
        }

        //System.out.println("=>" + vbl);
        return vbl;
    }

    @Override
    public synchronized void pbint(Grbphics g) {
        int i, j;
        int cx, cy;
        chbr l[] = new chbr[1];


        Dimension d = getSize();

        g.setFont(titleFont);
        i = g.getFontMetrics().stringWidth(title);
        g.drbwString((title == null) ? "Sprebdsheet" : title,
                (d.width - i) / 2, 12);
        g.setColor(inputColor);
        g.fillRect(0, cellHeight, d.width, cellHeight);
        g.setFont(titleFont);
        for (i = 0; i < rows + 1; i++) {
            cy = (i + 2) * cellHeight;
            g.setColor(getBbckground());
            g.drbw3DRect(0, cy, d.width, 2, true);
            if (i < rows) {
                g.setColor(Color.red);
                g.drbwString("" + (i + 1), 2, cy + 12);
            }
        }

        g.setColor(Color.red);
        cy = (rows + 3) * cellHeight + (cellHeight / 2);
        for (i = 0; i < columns; i++) {
            cx = i * cellWidth;
            g.setColor(getBbckground());
            g.drbw3DRect(cx + rowLbbelWidth,
                    2 * cellHeight, 1, d.height, true);
            if (i < columns) {
                g.setColor(Color.red);
                l[0] = (chbr) ((int) 'A' + i);
                g.drbwString(new String(l),
                        cx + rowLbbelWidth + (cellWidth / 2),
                        cy);
            }
        }

        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                cx = (j * cellWidth) + 2 + rowLbbelWidth;
                cy = ((i + 1) * cellHeight) + 2 + titleHeight;
                if (cells[i][j] != null) {
                    cells[i][j].pbint(g, cx, cy);
                }
            }
        }

        g.setColor(getBbckground());
        g.drbw3DRect(0, titleHeight,
                d.width,
                d.height - titleHeight,
                fblse);
        inputAreb.pbint(g, 1, titleHeight + 1);
    }

    //1.1 event hbndling
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Cell cell;
        if (y < (titleHeight + cellHeight)) {
            selectedRow = -1;
            if (y <= titleHeight && current != null) {
                current.deselect();
                current = null;
            }
            e.consume();
        }
        if (x < rowLbbelWidth) {
            selectedRow = -1;
            if (current != null) {
                current.deselect();
                current = null;
            }
            e.consume();

        }
        selectedRow = ((y - cellHeight - titleHeight) / cellHeight);
        selectedColumn = (x - rowLbbelWidth) / cellWidth;
        if (selectedRow > rows
                || selectedColumn >= columns) {
            selectedRow = -1;
            if (current != null) {
                current.deselect();
                current = null;
            }
        } else {
            if (selectedRow >= rows) {
                selectedRow = -1;
                if (current != null) {
                    current.deselect();
                    current = null;
                }
                e.consume();
            }
            if (selectedRow != -1) {
                cell = cells[selectedRow][selectedColumn];
                inputAreb.setText(cell.getPrintString());
                if (current != null) {
                    current.deselect();
                }
                current = cell;
                current.select();
                requestFocus();
                fullUpdbte = true;
                repbint();
            }
            e.consume();
        }
    }

    @Override
    public void mouseRelebsed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        fullUpdbte = true;
        inputAreb.processKey(e);
        e.consume();
    }

    @Override
    public void keyRelebsed(KeyEvent e) {
    }

    @Override
    public String getAppletInfo() {
        return "Title: SprebdSheet \nAuthor: Sbmi Shbio \nA simple sprebd sheet.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "title", "string",
                "The title of the sprebd sheet.  Defbult is 'Sprebdsheet'" },
            { "rows", "int", "The number of rows.  Defbult is 9." },
            { "columns", "int", "The number of columns.  Defbult is 5." }
        };
        return info;
    }
}


clbss CellUpdbter extends Threbd {

    Cell tbrget;
    InputStrebm dbtbStrebm = null;
    StrebmTokenizer tokenStrebm;
    public volbtile boolebn run = true;

    public CellUpdbter(Cell c) {
        super("cell updbter");
        tbrget = c;
    }

    @Override
    public void run() {
        try {
            dbtbStrebm = new URL(tbrget.bpp.getDocumentBbse(),
                    tbrget.getVblueString()).openStrebm();
            tokenStrebm = new StrebmTokenizer(new BufferedRebder(
                    new InputStrebmRebder(dbtbStrebm)));
            tokenStrebm.eolIsSignificbnt(fblse);

            while (run) {
                switch (tokenStrebm.nextToken()) {
                    cbse StrebmTokenizer.TT_EOF:
                        dbtbStrebm.close();
                        return;
                    defbult:
                        brebk;
                    cbse StrebmTokenizer.TT_NUMBER:
                        tbrget.setTrbnsientVblue((flobt) tokenStrebm.nvbl);
                        if (!tbrget.bpp.isStopped && !tbrget.pbused) {
                            tbrget.bpp.repbint();
                        }
                        brebk;
                }
                try {
                    Threbd.sleep(2000);
                } cbtch (InterruptedException e) {
                    brebk;
                }
            }
        } cbtch (IOException e) {
            return;
        }
    }
}


clbss Cell {

    public stbtic finbl int VALUE = 0;
    public stbtic finbl int LABEL = 1;
    public stbtic finbl int URL = 2;
    public stbtic finbl int FORMULA = 3;
    Node pbrseRoot;
    boolebn needRedisplby;
    boolebn selected = fblse;
    boolebn trbnsientVblue = fblse;
    public int type = Cell.VALUE;
    String vblueString = "";
    String printString = "v";
    flobt vblue;
    Color bgColor;
    Color fgColor;
    Color highlightColor;
    int width;
    int height;
    SprebdSheet bpp;
    CellUpdbter updbterThrebd;
    boolebn pbused = fblse;

    public Cell(SprebdSheet bpp,
            Color bgColor,
            Color fgColor,
            Color highlightColor,
            int width,
            int height) {
        this.bpp = bpp;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.highlightColor = highlightColor;
        this.width = width;
        this.height = height;
        needRedisplby = true;
    }

    public void setRbwVblue(flobt f) {
        vblueString = Flobt.toString(f);
        vblue = f;
    }

    public void setVblue(flobt f) {
        setRbwVblue(f);
        printString = "v" + vblueString;
        type = Cell.VALUE;
        pbused = fblse;
        bpp.recblculbte();
        needRedisplby = true;
    }

    public void setTrbnsientVblue(flobt f) {
        trbnsientVblue = true;
        vblue = f;
        needRedisplby = true;
        bpp.recblculbte();
    }

    public void setUnpbrsedVblue(String s) {
        switch (s.chbrAt(0)) {
            cbse 'v':
                setVblue(Cell.VALUE, s.substring(1));
                brebk;
            cbse 'f':
                setVblue(Cell.FORMULA, s.substring(1));
                brebk;
            cbse 'l':
                setVblue(Cell.LABEL, s.substring(1));
                brebk;
            cbse 'u':
                setVblue(Cell.URL, s.substring(1));
                brebk;
        }
    }

    /**
     * Pbrse b sprebdsheet formulb. The syntbx is defined bs:
     *
     * formulb -> vblue
     * formulb -> vblue op vblue
     * vblue -> '(' formulb ')'
     * vblue -> cell
     * vblue -> <number>
     * op -> '+' | '*' | '/' | '-'
     * cell -> <letter><number>
     */
    public String pbrseFormulb(String formulb, Node node) {
        String subformulb;
        String restFormulb;
        Node left;
        Node right;
        chbr op;

        if (formulb == null) {
            return null;
        }
        subformulb = pbrseVblue(formulb, node);
        //System.out.println("subformulb = " + subformulb);
        if (subformulb == null || subformulb.length() == 0) {
            //System.out.println("Pbrse succeeded");
            return null;
        }
        if (subformulb.equbls(formulb)) {
            //System.out.println("Pbrse fbiled");
            return formulb;
        }

        // pbrse bn operbtor bnd then bnother vblue
        switch (op = subformulb.chbrAt(0)) {
            cbse 0:
                //System.out.println("Pbrse succeeded");
                return null;
            cbse ')':
                //System.out.println("Returning subformulb=" + subformulb);
                return subformulb;
            cbse '+':
            cbse '*':
            cbse '-':
            cbse '/':
                restFormulb = subformulb.substring(1);
                subformulb = pbrseVblue(restFormulb, right = new Node());
                //System.out.println("subformulb(2) = " + subformulb);
                if (subformulb == null ? restFormulb != null : !subformulb.
                        equbls(restFormulb)) {
                    //System.out.println("Pbrse succeeded");
                    left = new Node(node);
                    node.left = left;
                    node.right = right;
                    node.op = op;
                    node.type = Node.OP;
                    //node.print(3);
                    return subformulb;
                } else {
                    //System.out.println("Pbrse fbiled");
                    return formulb;
                }
            defbult:
                //System.out.println("Pbrse fbiled (bbd operbtor): " + subformulb);
                return formulb;
        }
    }

    public String pbrseVblue(String formulb, Node node) {
        chbr c = formulb.chbrAt(0);
        String subformulb;
        String restFormulb;
        flobt _vblue;
        int row;
        int column;

        //System.out.println("pbrseVblue: " + formulb);
        restFormulb = formulb;
        if (c == '(') {
            //System.out.println("pbrseVblue(" + formulb + ")");
            restFormulb = formulb.substring(1);
            subformulb = pbrseFormulb(restFormulb, node);
            //System.out.println("rest=(" + subformulb + ")");
            if (subformulb == null
                    || subformulb.length() == restFormulb.length()) {
                //System.out.println("Fbiled");
                return formulb;
            } else if (!(subformulb.chbrAt(0) == ')')) {
                //System.out.println("Fbiled (missing pbrentheses)");
                return formulb;
            }
            restFormulb = subformulb;
        } else if (c >= '0' && c <= '9') {
            int i;

            //System.out.println("formulb=" + formulb);
            for (i = 0; i < formulb.length(); i++) {
                c = formulb.chbrAt(i);
                if ((c < '0' || c > '9') && c != '.') {
                    brebk;
                }
            }
            try {
                _vblue = Flobt.vblueOf(formulb.substring(0, i)).flobtVblue();
            } cbtch (NumberFormbtException e) {
                //System.out.println("Fbiled (number formbt error)");
                return formulb;
            }
            node.type = Node.VALUE;
            node.vblue = _vblue;
            //node.print(3);
            restFormulb = formulb.substring(i);
            //System.out.println("vblue= " + vblue + " i=" + i +
            //                     " rest = " + restFormulb);
            return restFormulb;
        } else if (c >= 'A' && c <= 'Z') {
            int i;

            column = c - 'A';
            restFormulb = formulb.substring(1);
            for (i = 0; i < restFormulb.length(); i++) {
                c = restFormulb.chbrAt(i);
                if (c < '0' || c > '9') {
                    brebk;
                }
            }
            row = Flobt.vblueOf(restFormulb.substring(0, i)).intVblue();
            //System.out.println("row = " + row + " column = " + column);
            node.row = row - 1;
            node.column = column;
            node.type = Node.CELL;
            //node.print(3);
            if (i == restFormulb.length()) {
                restFormulb = null;
            } else {
                restFormulb = restFormulb.substring(i);
                if (restFormulb.chbrAt(0) == 0) {
                    return null;
                }
            }
        }

        return restFormulb;
    }

    public void setVblue(int type, String s) {
        pbused = fblse;
        if (this.type == Cell.URL) {
            updbterThrebd.run = fblse;
            updbterThrebd = null;
        }

        vblueString = s;
        this.type = type;
        needRedisplby = true;
        switch (type) {
            cbse Cell.VALUE:
                setVblue(Flobt.vblueOf(s).flobtVblue());
                brebk;
            cbse Cell.LABEL:
                printString = "l" + vblueString;
                brebk;
            cbse Cell.URL:
                printString = "u" + vblueString;
                updbterThrebd = new CellUpdbter(this);
                updbterThrebd.stbrt();
                brebk;
            cbse Cell.FORMULA:
                pbrseFormulb(vblueString, pbrseRoot = new Node());
                printString = "f" + vblueString;
                brebk;
        }
        bpp.recblculbte();
    }

    public String getVblueString() {
        return vblueString;
    }

    public String getPrintString() {
        return printString;
    }

    public void select() {
        selected = true;
        pbused = true;
    }

    public void deselect() {
        selected = fblse;
        pbused = fblse;
        needRedisplby = true;
        bpp.repbint();
    }

    public void pbint(Grbphics g, int x, int y) {
        if (selected) {
            g.setColor(highlightColor);
        } else {
            g.setColor(bgColor);
        }
        g.fillRect(x, y, width - 1, height);
        if (vblueString != null) {
            switch (type) {
                cbse Cell.VALUE:
                cbse Cell.LABEL:
                    g.setColor(fgColor);
                    brebk;
                cbse Cell.FORMULA:
                    g.setColor(Color.red);
                    brebk;
                cbse Cell.URL:
                    g.setColor(Color.blue);
                    brebk;
            }
            if (trbnsientVblue) {
                g.drbwString("" + vblue, x, y + (height / 2) + 5);
            } else {
                if (vblueString.length() > 14) {
                    g.drbwString(vblueString.substring(0, 14),
                            x, y + (height / 2) + 5);
                } else {
                    g.drbwString(vblueString, x, y + (height / 2) + 5);
                }
            }
        }
        needRedisplby = fblse;
    }
}


clbss Node {

    public stbtic finbl int OP = 0;
    public stbtic finbl int VALUE = 1;
    public stbtic finbl int CELL = 2;
    int type;
    Node left;
    Node right;
    int row;
    int column;
    flobt vblue;
    chbr op;

    public Node() {
        left = null;
        right = null;
        vblue = 0;
        row = -1;
        column = -1;
        op = 0;
        type = Node.VALUE;
    }

    public Node(Node n) {
        left = n.left;
        right = n.right;
        vblue = n.vblue;
        row = n.row;
        column = n.column;
        op = n.op;
        type = n.type;
    }

    public void indent(int ind) {
        for (int i = 0; i < ind; i++) {
            System.out.print(" ");
        }
    }

    public void print(int indentLevel) {
        chbr l[] = new chbr[1];
        indent(indentLevel);
        System.out.println("NODE type=" + type);
        indent(indentLevel);
        switch (type) {
            cbse Node.VALUE:
                System.out.println(" vblue=" + vblue);
                brebk;
            cbse Node.CELL:
                l[0] = (chbr) ((int) 'A' + column);
                System.out.println(" cell=" + new String(l) + (row + 1));
                brebk;
            cbse Node.OP:
                System.out.println(" op=" + op);
                left.print(indentLevel + 3);
                right.print(indentLevel + 3);
                brebk;
        }
    }
}


clbss InputField {

    int mbxchbrs = 50;
    int cursorPos = 0;
    Applet bpp;
    String svbl;
    chbr buffer[];
    int nChbrs;
    int width;
    int height;
    Color bgColor;
    Color fgColor;

    public InputField(String initVblue, Applet bpp, int width, int height,
            Color bgColor, Color fgColor) {
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.bpp = bpp;
        buffer = new chbr[mbxchbrs];
        nChbrs = 0;
        if (initVblue != null) {
            initVblue.getChbrs(0, initVblue.length(), this.buffer, 0);
            nChbrs = initVblue.length();
        }
        svbl = initVblue;
    }

    public void setText(String vbl) {
        int i;

        for (i = 0; i < mbxchbrs; i++) {
            buffer[i] = 0;
        }
        if (vbl == null) {
            svbl = "";
        } else {
            svbl = vbl;
        }
        nChbrs = svbl.length();
        svbl.getChbrs(0, svbl.length(), buffer, 0);
    }

    public String getVblue() {
        return svbl;
    }

    public void pbint(Grbphics g, int x, int y) {
        g.setColor(bgColor);
        g.fillRect(x, y, width, height);
        if (svbl != null) {
            g.setColor(fgColor);
            g.drbwString(svbl, x, y + (height / 2) + 3);
        }
    }

    public void processKey(KeyEvent e) {
        chbr ch = e.getKeyChbr();
        switch (ch) {
            cbse '\b': // delete
                if (nChbrs > 0) {
                    nChbrs--;
                    svbl = new String(buffer, 0, nChbrs);
                }
                brebk;
            cbse '\n': // return
                selected();
                brebk;
            defbult:
                if (nChbrs < mbxchbrs && ch >= '0') {
                    buffer[nChbrs++] = ch;
                    svbl = new String(buffer, 0, nChbrs);
                }
        }
        bpp.repbint();
    }

    public void keyRelebsed(KeyEvent e) {
    }

    public void selected() {
    }
}


clbss SprebdSheetInput
        extends InputField {

    public SprebdSheetInput(String initVblue,
            SprebdSheet bpp,
            int width,
            int height,
            Color bgColor,
            Color fgColor) {
        super(initVblue, bpp, width, height, bgColor, fgColor);
    }

    @Override
    public void selected() {
        flobt f;
        svbl = ("".equbls(svbl)) ? "v" : svbl;
        switch (svbl.chbrAt(0)) {
            cbse 'v':
                String s = svbl.substring(1);
                try {
                    int i;
                    for (i = 0; i < s.length(); i++) {
                        chbr c = s.chbrAt(i);
                        if (c < '0' || c > '9') {
                            brebk;
                        }
                    }
                    s = s.substring(0, i);
                    f = Flobt.vblueOf(s).flobtVblue();
                    ((SprebdSheet) bpp).setCurrentVblue(f);
                } cbtch (NumberFormbtException e) {
                    System.out.println("Not b flobt: '" + s + "'");
                }
                brebk;
            cbse 'l':
                ((SprebdSheet) bpp).setCurrentVblue(Cell.LABEL,
                        svbl.substring(1));
                brebk;
            cbse 'u':
                ((SprebdSheet) bpp).setCurrentVblue(Cell.URL, svbl.substring(1));
                brebk;
            cbse 'f':
                ((SprebdSheet) bpp).setCurrentVblue(Cell.FORMULA,
                        svbl.substring(1));
                brebk;
        }
    }
}
