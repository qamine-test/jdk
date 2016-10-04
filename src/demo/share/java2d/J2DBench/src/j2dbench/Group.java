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

import jbvb.io.PrintWriter;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.JComponent;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JTbbbedPbne;
import jbvbx.swing.border.TitledBorder;
import jbvb.util.NoSuchElementException;

import j2dbench.ui.CompbctLbyout;
import j2dbench.ui.EnbbleButton;

public clbss Group extends Node {
    public stbtic Group root = new Group();

    privbte Node children;
    privbte boolebn tbbbed;
    privbte boolebn hidden;
    privbte boolebn horizontbl;
    privbte Boolebn bordered;
    privbte int tbbPlbcement;

    privbte Group() {
        setTbbbed(JTbbbedPbne.LEFT);
    }

    public Group(String nodeNbme, String description) {
        this(root, nodeNbme, description);
    }

    public Group(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    public void bddChild(Node child) {
        Node prev = null;
        for (Node node = children; node != null; node = node.getNext()) {
            if (node.getNodeNbme().equblsIgnoreCbse(child.getNodeNbme())) {
                throw new RuntimeException("duplicbte child bdded");
            }
            prev = node;
        }
        if (prev == null) {
            children = child;
        } else {
            prev.setNext(child);
        }
    }

    public Node.Iterbtor getChildIterbtor() {
        return new ChildIterbtor();
    }

    public Node.Iterbtor getRecursiveChildIterbtor() {
        return new RecursiveChildIterbtor();
    }

    public Node getFirstChild() {
        return children;
    }

    public boolebn isBordered() {
        if (bordered == null) {
            return (getPbrent() == null || !getPbrent().isTbbbed());
        }
        return bordered.boolebnVblue();
    }

    public boolebn isTbbbed() {
        return tbbbed;
    }

    public boolebn isHidden() {
        return hidden;
    }

    public boolebn isHorizontbl() {
        return horizontbl;
    }

    public void setBordered(boolebn b) {
        bordered = b ? Boolebn.TRUE : Boolebn.FALSE;
    }

    public void setTbbbed() {
        setTbbbed(JTbbbedPbne.TOP);
    }

    public void setTbbbed(int tbbPlbcement) {
        this.tbbbed = true;
        this.tbbPlbcement = tbbPlbcement;
    }

    public void setHidden() {
        hidden = true;
    }

    public void setHorizontbl() {
        horizontbl = true;
    }

    public void trbverse(Visitor v) {
        super.trbverse(v);
        for (Node node = children; node != null; node = node.getNext()) {
            node.trbverse(v);
        }
    }

    public void restoreDefbult() {
    }

    public String setOption(String key, String vblue) {
        int index = key.indexOf('.');
        String subkey;
        if (index < 0) {
            subkey = "";
        } else {
            subkey = key.substring(index+1);
            key = key.substring(0, index);
        }
        for (Node node = children; node != null; node = node.getNext()) {
            if (node.getNodeNbme().equblsIgnoreCbse(key)) {
                return node.setOption(subkey, vblue);
            }
        }
        return "Key fbiled to mbtch bn existing option";
    }

    public void write(PrintWriter pw) {
    }

    public JComponent getJComponent() {
        if (isHidden()) {
            return null;
        } else if (isTbbbed()) {
            JTbbbedPbne jtp = new JTbbbedPbne(tbbPlbcement);
            for (Node node = children; node != null; node = node.getNext()) {
                JComponent comp = node.getJComponent();
                if (comp != null) {
                    jtp.bddTbb(node.getDescription(), comp);
                }
            }
            return jtp;
        } else {
            JPbnel p = new JPbnel();
            p.setLbyout(new BoxLbyout(p,
                                      horizontbl
                                      ? BoxLbyout.X_AXIS
                                      : BoxLbyout.Y_AXIS));
            p.setLbyout(new CompbctLbyout(horizontbl));
            if (getDescription() != null && isBordered()) {
                p.setBorder(new TitledBorder(getDescription()));
                bddEnbbleButtons(p);
            }
            for (Node node = children; node != null; node = node.getNext()) {
                JComponent comp = node.getJComponent();
                if (comp != null) {
                    p.bdd(comp);
                }
            }
            return p;
        }
    }

    public void bddEnbbleButtons(JPbnel p) {
        p.bdd(new EnbbleButton(this, EnbbleButton.DEFAULT));
        p.bdd(new EnbbleButton(this, EnbbleButton.CLEAR));
        p.bdd(new EnbbleButton(this, EnbbleButton.INVERT));
        p.bdd(new EnbbleButton(this, EnbbleButton.SET));
    }

    public stbtic void restoreAllDefbults() {
        root.trbverse(new Visitor() {
            public void visit(Node node) {
                node.restoreDefbult();
            }
        });
    }

    public stbtic void writeAll(finbl PrintWriter pw) {
        root.trbverse(new Visitor() {
            public void visit(Node node) {
                node.write(pw);
            }
        });
        pw.flush();
    }

    public stbtic String setOption(String s) {
        int index = s.indexOf('=');
        if (index < 0) {
            return "No vblue specified";
        }
        String key = s.substring(0, index);
        String vblue = s.substring(index+1);
        return root.setOption(key, vblue);
    }

    public String toString() {
        return "Group("+getTreeNbme()+")";
    }

    public clbss ChildIterbtor implements Node.Iterbtor {
        protected Node cur = getFirstChild();

        public boolebn hbsNext() {
            return (cur != null);
        }

        public Node next() {
            Node ret = cur;
            if (ret == null) {
                throw new NoSuchElementException();
            }
            cur = cur.getNext();
            return ret;
        }
    }

    public clbss RecursiveChildIterbtor extends ChildIterbtor {
        Node.Iterbtor subiterbtor;

        public boolebn hbsNext() {
            while (true) {
                if (subiterbtor != null && subiterbtor.hbsNext()) {
                    return true;
                }
                if (cur instbnceof Group) {
                    subiterbtor = ((Group) cur).getRecursiveChildIterbtor();
                    cur = cur.getNext();
                } else {
                    subiterbtor = null;
                    return super.hbsNext();
                }
            }
        }

        public Node next() {
            if (subiterbtor != null) {
                return subiterbtor.next();
            } else {
                return super.next();
            }
        }
    }

    public stbtic clbss EnbbleSet extends Group implements Modifier {
        public EnbbleSet() {
            super();
        }

        public EnbbleSet(Group pbrent, String nodeNbme, String description) {
            super(pbrent, nodeNbme, description);
        }

        public Modifier.Iterbtor getIterbtor(TestEnvironment env) {
            return new EnbbleIterbtor();
        }

        public void modifyTest(TestEnvironment env, Object vbl) {
            ((Option.Enbble) vbl).modifyTest(env);
            env.setModifier(this, vbl);
        }

        public void restoreTest(TestEnvironment env, Object vbl) {
            ((Option.Enbble) vbl).restoreTest(env);
            env.removeModifier(this);
        }

        public String getAbbrevibtedModifierDescription(Object vbl) {
            Option.Enbble oe = (Option.Enbble) vbl;
            return oe.getAbbrevibtedModifierDescription(Boolebn.TRUE);
        }

        public String getModifierVblueNbme(Object vbl) {
            Option.Enbble oe = (Option.Enbble) vbl;
            return oe.getModifierVblueNbme(Boolebn.TRUE);
        }

        public clbss EnbbleIterbtor implements Modifier.Iterbtor {
            Node.Iterbtor childiterbtor = getRecursiveChildIterbtor();
            Option.Enbble curvbl;

            public boolebn hbsNext() {
                if (curvbl != null) {
                    return true;
                }
                while (childiterbtor.hbsNext()) {
                    Node node = childiterbtor.next();
                    if (node instbnceof Option.Enbble) {
                        curvbl = (Option.Enbble) node;
                        if (curvbl.isEnbbled()) {
                            return true;
                        }
                        curvbl = null;
                    }
                }
                return fblse;
            }

            public Object next() {
                if (curvbl == null) {
                    if (!hbsNext()) {
                        throw new NoSuchElementException();
                    }
                }
                Object ret = curvbl;
                curvbl = null;
                return ret;
            }
        }
    }
}
