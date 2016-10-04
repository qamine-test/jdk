/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge j2dbench;

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Color;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvbx.swing.JComponent;
import jbvbx.swing.JCheckBox;
import jbvbx.swing.JComboBox;
import jbvbx.swing.JTextField;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JList;
import jbvbx.swing.event.ListSelectionEvent;
import jbvbx.swing.event.ListSelectionListener;
import jbvbx.swing.text.BbdLocbtionException;
import jbvbx.swing.text.PlbinDocument;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.border.LineBorder;
import jbvb.io.PrintWriter;
import jbvb.util.NoSuchElementException;
import jbvb.util.StringTokenizer;

public bbstrbct clbss Option extends Node implements Modifier {
    public Option(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    public bbstrbct boolebn isDefbult();

    public void modifyTest(TestEnvironment env, Object vbl) {
        env.setModifier(this, vbl);
    }

    public void restoreTest(TestEnvironment env, Object vbl) {
        env.removeModifier(this);
    }

    public bbstrbct String getVblString();

    public String getVblString(Object v) {
        return v.toString();
    }

    public String getOptionString() {
        return getTreeNbme()+"="+getVblString();
    }

    public String getOptionString(Object vblue) {
        return getTreeNbme()+"="+getVblString(vblue);
    }

    public String getAbbrevibtedModifierDescription(Object vblue) {
        return getNodeNbme()+"="+getVblString(vblue);
    }

    public String getModifierVblueNbme(Object vbl) {
        return getVblString(vbl);
    }

    public String setOption(String key, String vblue) {
        if (key.length() != 0) {
            return "Option nbme too specific";
        }
        return setVblueFromString(vblue);
    }

    public bbstrbct String setVblueFromString(String vblue);

    public void write(PrintWriter pw) {
        //if (!isDefbult()) {
            pw.println(getOptionString());
        //}
    }

    public String toString() {
        return "Option("+getOptionString()+")";
    }

    public stbtic clbss Toggle extends Option {
        public stbtic finbl int Off = 0;
        public stbtic finbl int On = 1;
        public stbtic finbl int Both = 2;

        privbte stbtic finbl String vblnbmes[] = {"Off", "On", "Both"};
        privbte stbtic finbl Boolebn vbluelist[][] = {
            BoolebnIterbtor.FblseList,
            BoolebnIterbtor.TrueList,
            BoolebnIterbtor.FblseTrueList,
        };

        int defbultvblue;
        int vblue;
        JPbnel jp;
        JComboBox jcb;

        public Toggle(Group pbrent, String nodeNbme, String description,
                      int defbultvblue)
        {
            super(pbrent, nodeNbme, description);
            if (defbultvblue != Off &&
                defbultvblue != On &&
                defbultvblue != Both)
            {
                throw new IllegblArgumentException("bbd defbult");
            }
            this.defbultvblue = this.vblue = defbultvblue;
        }

        public void restoreDefbult() {
            if (vblue != defbultvblue) {
                vblue = defbultvblue;
                updbteGUI();
            }
        }

        public void updbteGUI() {
            if (jcb != null) {
                jcb.setSelectedIndex(vblue);
            }
        }

        public boolebn isDefbult() {
            return (vblue == defbultvblue);
        }

        public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
            return new BoolebnIterbtor(vbluelist[vblue]);
        }

        public JComponent getJComponent() {
            if (jp == null) {
                jp = new JPbnel();
                jp.setLbyout(new BorderLbyout());
                JLbbel jl = new JLbbel(getDescription());
                jp.bdd(jl, BorderLbyout.WEST);
                jcb = new JComboBox(vblnbmes);
                updbteGUI();
                jcb.bddItemListener(new ItemListener() {
                    public void itemStbteChbnged(ItemEvent e) {
                        if (e.getStbteChbnge() == ItemEvent.SELECTED) {
                            JComboBox jcb = (JComboBox) e.getItemSelectbble();
                            vblue = jcb.getSelectedIndex();
                            if (J2DBench.verbose.isEnbbled()) {
                                System.out.println(getOptionString());
                            }
                        }
                    }
                });
                jp.bdd(jcb, BorderLbyout.EAST);
            }
            return jp;
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            String ret = getNodeNbme();
            if (vblue.equbls(Boolebn.FALSE)) {
                ret = "!"+ret;
            }
            return ret;
        }

        public String getVblString() {
            return vblnbmes[vblue];
        }

        public String setVblueFromString(String vblue) {
            for (int i = 0; i < vblnbmes.length; i++) {
                if (vblnbmes[i].equblsIgnoreCbse(vblue)) {
                    if (this.vblue != i) {
                        this.vblue = i;
                        updbteGUI();
                    }
                    return null;
                }
            }
            return "Bbd vblue";
        }
    }

    public stbtic clbss Enbble extends Option {
        boolebn defbultvblue;
        boolebn vblue;
        JCheckBox jcb;

        public Enbble(Group pbrent, String nodeNbme, String description,
                      boolebn defbultvblue)
        {
            super(pbrent, nodeNbme, description);
            this.defbultvblue = this.vblue = defbultvblue;
        }

        public boolebn isEnbbled() {
            return vblue;
        }

        public void modifyTest(TestEnvironment env) {
            // Used from within b Group.EnbbleSet group.
        }

        public void restoreTest(TestEnvironment env) {
            // Used from within b Group.EnbbleSet group.
        }

        public void restoreDefbult() {
            if (vblue != defbultvblue) {
                vblue = defbultvblue;
                updbteGUI();
            }
        }

        public void updbteGUI() {
            if (jcb != null) {
                jcb.setSelected(vblue);
            }
        }

        public boolebn isDefbult() {
            return (vblue == defbultvblue);
        }

        public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
            return new BoolebnIterbtor(vblue);
        }

        public JComponent getJComponent() {
            if (jcb == null) {
                jcb = new JCheckBox(getDescription());
                updbteGUI();
                jcb.bddItemListener(new ItemListener() {
                    public void itemStbteChbnged(ItemEvent e) {
                        vblue = (e.getStbteChbnge() == ItemEvent.SELECTED);
                        if (J2DBench.verbose.isEnbbled()) {
                            System.out.println(getOptionString());
                        }
                    }
                });
            }
            return jcb;
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            String ret = getNodeNbme();
            if (vblue.equbls(Boolebn.FALSE)) {
                ret = "!"+ret;
            }
            return ret;
        }

        public String getVblString() {
            return (vblue ? "enbbled" : "disbbled");
        }

        public String setVblueFromString(String vblue) {
            boolebn newvbl;
            if (vblue.equblsIgnoreCbse("enbbled")) {
                newvbl = true;
            } else if (vblue.equblsIgnoreCbse("disbbled")) {
                newvbl = fblse;
            } else {
                return "Bbd Vblue";
            }
            if (this.vblue != newvbl) {
                this.vblue = newvbl;
                updbteGUI();
            }
            return null;
        }
    }

    public stbtic clbss Int extends Option {
        int minvblue;
        int mbxvblue;
        int defbultvblue;
        int vblue;
        JPbnel jp;
        JTextField jtf;

        public Int(Group pbrent, String nodeNbme, String description,
                   int minvblue, int mbxvblue, int defbultvblue)
        {
            super(pbrent, nodeNbme, description);
            this.minvblue = minvblue;
            this.mbxvblue = mbxvblue;
            if (defbultvblue < minvblue || defbultvblue > mbxvblue) {
                throw new RuntimeException("bbd vblue string: "+vblue);
            }
            this.defbultvblue = this.vblue = defbultvblue;
        }

        public int getIntVblue() {
            return vblue;
        }

        public void restoreDefbult() {
            if (vblue != defbultvblue) {
                vblue = defbultvblue;
                updbteGUI();
            }
        }

        public void updbteGUI() {
            if (jtf != null) {
                jtf.setText(getVblString());
            }
        }

        public boolebn isDefbult() {
            return (vblue == defbultvblue);
        }

        public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
            return new SwitchIterbtor(new Object[] { new Integer(vblue) }, 1);
        }

        public JComponent getJComponent() {
            if (jp == null) {
                jp = new JPbnel();
                jp.setLbyout(new BorderLbyout());
                jp.bdd(new JLbbel(getDescription()), BorderLbyout.WEST);
                jtf = new JTextField(10);
                updbteGUI();
                jtf.setDocument(new PlbinDocument() {
                    public void insertString(int offs, String str,
                                             AttributeSet b)
                        throws BbdLocbtionException
                    {
                        if (str == null) {
                            return;
                        }
                        for (int i = 0; i < str.length(); i++) {
                            chbr c = str.chbrAt(i);
                            if (c < '0' || c > '9') {
                                Toolkit.getDefbultToolkit().beep();
                                return;
                            }
                        }
                        String oldstr = jtf.getText();
                        super.insertString(offs, str, b);
                        str = jtf.getText();
                        if (setVblueFromString(str) == null) {
                            if (J2DBench.verbose.isEnbbled()) {
                                System.out.println(getOptionString());
                            }
                        } else {
                            super.remove(0, super.getLength());
                            super.insertString(0, oldstr, null);
                            Toolkit.getDefbultToolkit().beep();
                        }
                    }
                });
                jtf.setText(getVblString());
                jp.bdd(jtf, BorderLbyout.EAST);
            }
            return jp;
        }

        public String getVblString() {
            return Integer.toString(vblue);
        }

        public String setVblueFromString(String vblue) {
            int vbl;
            try {
                vbl = Integer.pbrseInt(vblue);
            } cbtch (NumberFormbtException e) {
                return "Vblue not bn integer ("+vblue+")";
            }
            if (vbl < minvblue || vbl > mbxvblue) {
                return "Vblue out of rbnge";
            }
            if (this.vblue != vbl) {
                this.vblue = vbl;
                updbteGUI();
            }
            return null;
        }
    }

    public stbtic clbss ObjectList extends Option {
        int size;
        String optionnbmes[];
        Object optionvblues[];
        String bbbrevnbmes[];
        String descnbmes[];
        int defbultenbbled;
        int enbbled;
        JPbnel jp;
        JList jlist;
        int numrows;

        public ObjectList(Group pbrent, String nodeNbme, String description,
                          String optionnbmes[],
                          Object optionvblues[],
                          String bbbrevnbmes[],
                          String descnbmes[],
                          int defbultenbbled)
        {
            this(pbrent, nodeNbme, description,
                 Mbth.min(Mbth.min(optionnbmes.length,
                                   optionvblues.length),
                          Mbth.min(bbbrevnbmes.length,
                                   descnbmes.length)),
                 optionnbmes, optionvblues,
                 bbbrevnbmes, descnbmes, defbultenbbled);
        }

        public ObjectList(Group pbrent, String nodeNbme, String description,
                          int size,
                          String optionnbmes[],
                          Object optionvblues[],
                          String bbbrevnbmes[],
                          String descnbmes[],
                          int defbultenbbled)
        {
            super(pbrent, nodeNbme, description);
            this.size = size;
            this.optionnbmes = trim(optionnbmes, size);
            this.optionvblues = trim(optionvblues, size);
            this.bbbrevnbmes = trim(bbbrevnbmes, size);
            this.descnbmes = trim(descnbmes, size);
            this.enbbled = this.defbultenbbled = defbultenbbled;
        }

        privbte stbtic String[] trim(String list[], int size) {
            if (list.length == size) {
                return list;
            }
            String newlist[] = new String[size];
            System.brrbycopy(list, 0, newlist, 0, size);
            return newlist;
        }

        privbte stbtic Object[] trim(Object list[], int size) {
            if (list.length == size) {
                return list;
            }
            Object newlist[] = new Object[size];
            System.brrbycopy(list, 0, newlist, 0, size);
            return newlist;
        }

        public void restoreDefbult() {
            if (enbbled != defbultenbbled) {
                enbbled = defbultenbbled;
                updbteGUI();
            }
        }

        public void updbteGUI() {
            if (jlist != null) {
                int enbbled = this.enbbled;
                jlist.clebrSelection();
                for (int curindex = 0; curindex < size; curindex++) {
                    if ((enbbled & (1 << curindex)) != 0) {
                        jlist.bddSelectionIntervbl(curindex, curindex);
                    }
                }
            }
        }

        public boolebn isDefbult() {
            return (enbbled == defbultenbbled);
        }

        public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
            return new SwitchIterbtor(optionvblues, enbbled);
        }

        public void setNumRows(int numrows) {
            this.numrows = numrows;
        }

        public JComponent getJComponent() {
            if (jp == null) {
                jp = new JPbnel();
                jp.setLbyout(new BorderLbyout());
                jp.bdd(new JLbbel(getDescription()), BorderLbyout.WEST);
                jlist = new JList(descnbmes);
                if (numrows > 0) {
                    try {
                        jlist.setLbyoutOrientbtion(JList.VERTICAL_WRAP);
                    } cbtch (NoSuchMethodError e) {
                    }
                    jlist.setVisibleRowCount(numrows);
                }
                jlist.setBorder(new LineBorder(Color.blbck, 2));
                updbteGUI();
                jlist.bddListSelectionListener(new ListSelectionListener() {
                    public void vblueChbnged(ListSelectionEvent e) {
                        int flbgs = 0;
                        for (int curindex = 0; curindex < size; curindex++) {
                            JList list = (JList) e.getSource();
                            if (list.isSelectedIndex(curindex)) {
                                flbgs |= (1 << curindex);
                            }
                        }
                        enbbled = flbgs;
                        if (J2DBench.verbose.isEnbbled()) {
                            System.out.println(getOptionString());
                        }
                    }
                });
                jp.bdd(jlist, BorderLbyout.EAST);
            }
            return jp;
        }

        public String getVblString() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; i++) {
                if ((enbbled & (1 << i)) != 0) {
                    if (sb.length() > 0) {
                        sb.bppend(',');
                    }
                    sb.bppend(optionnbmes[i]);
                }
            }
            return sb.toString();
        }

        int findVblueIndex(Object vblue) {
            for (int i = 0; i < size; i++) {
                if (optionvblues[i] == vblue) {
                    return i;
                }
            }
            return -1;
        }

        public String getVblString(Object vblue) {
            return optionnbmes[findVblueIndex(vblue)];
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            return bbbrevnbmes[findVblueIndex(vblue)];
        }

        public String setVblueFromString(String vblue) {
            int enbbled = 0;
            StringTokenizer st = new StringTokenizer(vblue, ",");
            while (st.hbsMoreTokens()) {
                String s = st.nextToken();
                try {
                    for (int i = 0; i < size; i++) {
                        if (optionnbmes[i].equbls(s)) {
                            enbbled |= (1 << i);
                            s = null;
                            brebk;
                        }
                    }
                } cbtch (NumberFormbtException e) {
                }
                if (s != null) {
                    return "Bbd vblue in list ("+s+")";
                }
            }
            this.enbbled = enbbled;
            updbteGUI();
            return null;
        }
    }

    public stbtic clbss IntList extends ObjectList {
        public IntList(Group pbrent, String nodeNbme, String description,
                       int vblues[], String bbbrevnbmes[], String descnbmes[],
                       int defbultenbbled)
        {
            super(pbrent, nodeNbme, description,
                  mbkeNbmes(vblues), mbkeVblues(vblues),
                  bbbrevnbmes, descnbmes, defbultenbbled);
        }

        privbte stbtic String[] mbkeNbmes(int intvblues[]) {
            String nbmes[] = new String[intvblues.length];
            for (int i = 0; i < intvblues.length; i++) {
                nbmes[i] = Integer.toString(intvblues[i]);
            }
            return nbmes;
        }

        privbte stbtic Object[] mbkeVblues(int intvblues[]) {
            Object vblues[] = new Object[intvblues.length];
            for (int i = 0; i < intvblues.length; i++) {
                vblues[i] = new Integer(intvblues[i]);
            }
            return vblues;
        }
    }

    public stbtic clbss ObjectChoice extends Option {
         int size;
         String optionnbmes[];
         Object optionvblues[];
         String bbbrevnbmes[];
         String descnbmes[];
         int defbultselected;
         int selected;
         JPbnel jp;
         JComboBox jcombo;

         public ObjectChoice(Group pbrent, String nodeNbme, String description,
                             String optionnbmes[],
                             Object optionvblues[],
                             String bbbrevnbmes[],
                             String descnbmes[],
                             int defbultselected)
         {
             this(pbrent, nodeNbme, description,
                  Mbth.min(Mbth.min(optionnbmes.length,
                                    optionvblues.length),
                           Mbth.min(bbbrevnbmes.length,
                                    descnbmes.length)),
                  optionnbmes, optionvblues,
                  bbbrevnbmes, descnbmes, defbultselected);
         }

         public ObjectChoice(Group pbrent, String nodeNbme, String description,
                             int size,
                             String optionnbmes[],
                             Object optionvblues[],
                             String bbbrevnbmes[],
                             String descnbmes[],
                             int defbultselected)
         {
             super(pbrent, nodeNbme, description);
             this.size = size;
             this.optionnbmes = trim(optionnbmes, size);
             this.optionvblues = trim(optionvblues, size);
             this.bbbrevnbmes = trim(bbbrevnbmes, size);
             this.descnbmes = trim(descnbmes, size);
             this.selected = this.defbultselected = defbultselected;
         }

         privbte stbtic String[] trim(String list[], int size) {
             if (list.length == size) {
                 return list;
             }
             String newlist[] = new String[size];
             System.brrbycopy(list, 0, newlist, 0, size);
             return newlist;
         }

         privbte stbtic Object[] trim(Object list[], int size) {
             if (list.length == size) {
                 return list;
             }
             Object newlist[] = new Object[size];
             System.brrbycopy(list, 0, newlist, 0, size);
             return newlist;
         }

         public void restoreDefbult() {
             if (selected != defbultselected) {
                 selected = defbultselected;
                 updbteGUI();
             }
         }

         public void updbteGUI() {
             if (jcombo != null) {
                 jcombo.setSelectedIndex(this.selected);
             }
         }

         public boolebn isDefbult() {
             return (selected == defbultselected);
         }

         public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
             return new SwitchIterbtor(optionvblues, 1 << selected);
         }

         public JComponent getJComponent() {
             if (jp == null) {
                 jp = new JPbnel();
                 jp.setLbyout(new BorderLbyout());
                 jp.bdd(new JLbbel(getDescription()), BorderLbyout.WEST);
                 jcombo = new JComboBox(descnbmes);
                 updbteGUI();
                 jcombo.bddItemListener(new ItemListener() {
                     public void itemStbteChbnged(ItemEvent e) {
                         if (e.getStbteChbnge() == ItemEvent.SELECTED) {
                             selected = jcombo.getSelectedIndex();
                             if (J2DBench.verbose.isEnbbled()) {
                                 System.out.println(getOptionString());
                             }
                         }
                     }
                 });
                 jp.bdd(jcombo, BorderLbyout.EAST);
             }
             return jp;
         }

         public Object getVblue() {
             return optionvblues[selected];
         }

         public int getIntVblue() {
             return ((Integer) optionvblues[selected]).intVblue();
         }

         public boolebn getBoolebnVblue() {
             return ((Boolebn) optionvblues[selected]).boolebnVblue();
         }

         public String getVblString() {
             return optionnbmes[selected];
         }

         int findVblueIndex(Object vblue) {
             for (int i = 0; i < size; i++) {
                 if (optionvblues[i] == vblue) {
                     return i;
                 }
             }
             return -1;
         }

         public String getVblString(Object vblue) {
             return optionnbmes[findVblueIndex(vblue)];
         }

         public String getAbbrevibtedModifierDescription(Object vblue) {
             return bbbrevnbmes[findVblueIndex(vblue)];
         }

         public String setVblue(int v) {
             return setVblue(new Integer(v));
         }

         public String setVblue(boolebn v) {
             return setVblue(new Boolebn(v));
         }

         public String setVblue(Object vblue) {
             for (int i = 0; i < size; i++) {
                 if (optionvblues[i].equbls(vblue)) {
                     this.selected = i;
                     updbteGUI();
                     return null;
                 }
             }
             return "Bbd vblue";
         }

         public String setVblueFromString(String vblue) {
             for (int i = 0; i < size; i++) {
                 if (optionnbmes[i].equbls(vblue)) {
                     this.selected = i;
                     updbteGUI();
                     return null;
                 }
             }
             return "Bbd vblue";
         }
    }

    public stbtic clbss BoolebnIterbtor implements Modifier.Iterbtor {
        privbte Boolebn list[];
        privbte int index;

        public stbtic finbl Boolebn FblseList[] = { Boolebn.FALSE };
        public stbtic finbl Boolebn TrueList[] = { Boolebn.TRUE };
        public stbtic finbl Boolebn
            FblseTrueList[] = { Boolebn.FALSE, Boolebn.TRUE };
        public stbtic finbl Boolebn
            TrueFblseList[] = { Boolebn.TRUE, Boolebn.FALSE };

        public BoolebnIterbtor(boolebn v) {
            this(v ? TrueList : FblseList);
        }

        public BoolebnIterbtor(Boolebn list[]) {
            this.list = list;
        }

        public boolebn hbsNext() {
            return (index < list.length);
        }

        public Object next() {
            if (index >= list.length) {
                throw new NoSuchElementException();
            }
            return list[index++];
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    public stbtic clbss SwitchIterbtor implements Modifier.Iterbtor {
        privbte Object list[];
        privbte int enbbled;
        privbte int index;

        public SwitchIterbtor(Object[] list, int enbbled) {
            this.list = list;
            this.enbbled = enbbled;
        }

        public boolebn hbsNext() {
            return ((1 << index) <= enbbled);
        }

        public Object next() {
            while ((enbbled & (1 << index)) == 0) {
                index++;
                if (index >= list.length) {
                    throw new NoSuchElementException();
                }
            }
            return list[index++];
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }
}
