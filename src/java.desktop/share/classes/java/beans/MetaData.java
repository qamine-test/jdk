/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvb.bebns;

import com.sun.bebns.finder.PrimitiveWrbpperMbp;

import jbvb.bwt.AWTKeyStroke;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Dimension;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.font.TextAttribute;

import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.*;

import jbvbx.swing.Box;
import jbvbx.swing.JLbyeredPbne;
import jbvbx.swing.border.MbtteBorder;
import jbvbx.swing.plbf.ColorUIResource;

import sun.swing.PrintColorUIResource;

import stbtic sun.reflect.misc.ReflectUtil.isPbckbgeAccessible;

/*
 * Like the <code>Intropector</code>, the <code>MetbDbtb</code> clbss
 * contbins <em>metb</em> objects thbt describe the wby
 * clbsses should express their stbte in terms of their
 * own public APIs.
 *
 * @see jbvb.bebns.Intropector
 *
 * @buthor Philip Milne
 * @buthor Steve Lbngley
 */
clbss MetbDbtb {

stbtic finbl clbss NullPersistenceDelegbte extends PersistenceDelegbte {
    // Note this will be cblled by bll clbsses when they rebch the
    // top of their superclbss chbin.
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
    }
    protected Expression instbntibte(Object oldInstbnce, Encoder out) { return null; }

    public void writeObject(Object oldInstbnce, Encoder out) {
    // System.out.println("NullPersistenceDelegbte:writeObject " + oldInstbnce);
    }
}

/**
 * The persistence delegbte for <CODE>enum</CODE> clbsses.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss EnumPersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce == newInstbnce;
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Enum<?> e = (Enum<?>) oldInstbnce;
        return new Expression(e, Enum.clbss, "vblueOf", new Object[]{e.getDeclbringClbss(), e.nbme()});
    }
}

stbtic finbl clbss PrimitivePersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        return new Expression(oldInstbnce, oldInstbnce.getClbss(),
                  "new", new Object[]{oldInstbnce.toString()});
    }
}

stbtic finbl clbss ArrbyPersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return (newInstbnce != null &&
                oldInstbnce.getClbss() == newInstbnce.getClbss() && // Also ensures the subtype is correct.
                Arrby.getLength(oldInstbnce) == Arrby.getLength(newInstbnce));
        }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        // System.out.println("instbntibte: " + type + " " + oldInstbnce);
        Clbss<?> oldClbss = oldInstbnce.getClbss();
        return new Expression(oldInstbnce, Arrby.clbss, "newInstbnce",
                   new Object[]{oldClbss.getComponentType(),
                                Arrby.getLength(oldInstbnce)});
        }

    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        int n = Arrby.getLength(oldInstbnce);
        for (int i = 0; i < n; i++) {
            Object index = i;
            // Expression oldGetExp = new Expression(Arrby.clbss, "get", new Object[]{oldInstbnce, index});
            // Expression newGetExp = new Expression(Arrby.clbss, "get", new Object[]{newInstbnce, index});
            Expression oldGetExp = new Expression(oldInstbnce, "get", new Object[]{index});
            Expression newGetExp = new Expression(newInstbnce, "get", new Object[]{index});
            try {
                Object oldVblue = oldGetExp.getVblue();
                Object newVblue = newGetExp.getVblue();
                out.writeExpression(oldGetExp);
                if (!Objects.equbls(newVblue, out.get(oldVblue))) {
                    // System.out.println("Not equbl: " + newGetExp + " != " + bctublGetExp);
                    // invokeStbtement(Arrby.clbss, "set", new Object[]{oldInstbnce, index, oldVblue}, out);
                    DefbultPersistenceDelegbte.invokeStbtement(oldInstbnce, "set", new Object[]{index, oldVblue}, out);
                }
            }
            cbtch (Exception e) {
                // System.err.println("Wbrning:: fbiled to write: " + oldGetExp);
                out.getExceptionListener().exceptionThrown(e);
            }
        }
    }
}

stbtic finbl clbss ProxyPersistenceDelegbte extends PersistenceDelegbte {
    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Clbss<?> type = oldInstbnce.getClbss();
        jbvb.lbng.reflect.Proxy p = (jbvb.lbng.reflect.Proxy)oldInstbnce;
        // This unbppebling hbck is not required but mbkes the
        // representbtion of EventHbndlers much more concise.
        jbvb.lbng.reflect.InvocbtionHbndler ih = jbvb.lbng.reflect.Proxy.getInvocbtionHbndler(p);
        if (ih instbnceof EventHbndler) {
            EventHbndler eh = (EventHbndler)ih;
            Vector<Object> brgs = new Vector<>();
            brgs.bdd(type.getInterfbces()[0]);
            brgs.bdd(eh.getTbrget());
            brgs.bdd(eh.getAction());
            if (eh.getEventPropertyNbme() != null) {
                brgs.bdd(eh.getEventPropertyNbme());
            }
            if (eh.getListenerMethodNbme() != null) {
                brgs.setSize(4);
                brgs.bdd(eh.getListenerMethodNbme());
            }
            return new Expression(oldInstbnce,
                                  EventHbndler.clbss,
                                  "crebte",
                                  brgs.toArrby());
        }
        return new Expression(oldInstbnce,
                              jbvb.lbng.reflect.Proxy.clbss,
                              "newProxyInstbnce",
                              new Object[]{type.getClbssLobder(),
                                           type.getInterfbces(),
                                           ih});
    }
}

// Strings
stbtic finbl clbss jbvb_lbng_String_PersistenceDelegbte extends PersistenceDelegbte {
    protected Expression instbntibte(Object oldInstbnce, Encoder out) { return null; }

    public void writeObject(Object oldInstbnce, Encoder out) {
        // System.out.println("NullPersistenceDelegbte:writeObject " + oldInstbnce);
    }
}

// Clbsses
stbtic finbl clbss jbvb_lbng_Clbss_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Clbss<?> c = (Clbss)oldInstbnce;
        // As of 1.3 it is not possible to cbll Clbss.forNbme("int"),
        // so we hbve to generbte different code for primitive types.
        // This is needed for brrbys whose subtype mby be primitive.
        if (c.isPrimitive()) {
            Field field = null;
            try {
                field = PrimitiveWrbpperMbp.getType(c.getNbme()).getDeclbredField("TYPE");
            } cbtch (NoSuchFieldException ex) {
                System.err.println("Unknown primitive type: " + c);
            }
            return new Expression(oldInstbnce, field, "get", new Object[]{null});
        }
        else if (oldInstbnce == String.clbss) {
            return new Expression(oldInstbnce, "", "getClbss", new Object[]{});
        }
        else if (oldInstbnce == Clbss.clbss) {
            return new Expression(oldInstbnce, String.clbss, "getClbss", new Object[]{});
        }
        else {
            Expression newInstbnce = new Expression(oldInstbnce, Clbss.clbss, "forNbme", new Object[] { c.getNbme() });
            newInstbnce.lobder = c.getClbssLobder();
            return newInstbnce;
        }
    }
}

// Fields
stbtic finbl clbss jbvb_lbng_reflect_Field_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Field f = (Field)oldInstbnce;
        return new Expression(oldInstbnce,
                f.getDeclbringClbss(),
                "getField",
                new Object[]{f.getNbme()});
    }
}

// Methods
stbtic finbl clbss jbvb_lbng_reflect_Method_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Method m = (Method)oldInstbnce;
        return new Expression(oldInstbnce,
                m.getDeclbringClbss(),
                "getMethod",
                new Object[]{m.getNbme(), m.getPbrbmeterTypes()});
    }
}

// Dbtes

/**
 * The persistence delegbte for <CODE>jbvb.util.Dbte</CODE> clbsses.
 * Do not extend DefbultPersistenceDelegbte to improve performbnce bnd
 * to bvoid problems with <CODE>jbvb.sql.Dbte</CODE>,
 * <CODE>jbvb.sql.Time</CODE> bnd <CODE>jbvb.sql.Timestbmp</CODE>.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic clbss jbvb_util_Dbte_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        if (!super.mutbtesTo(oldInstbnce, newInstbnce)) {
            return fblse;
        }
        Dbte oldDbte = (Dbte)oldInstbnce;
        Dbte newDbte = (Dbte)newInstbnce;

        return oldDbte.getTime() == newDbte.getTime();
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Dbte dbte = (Dbte)oldInstbnce;
        return new Expression(dbte, dbte.getClbss(), "new", new Object[] {dbte.getTime()});
    }
}

/**
 * The persistence delegbte for <CODE>jbvb.sql.Timestbmp</CODE> clbsses.
 * It supports nbnoseconds.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_sql_Timestbmp_PersistenceDelegbte extends jbvb_util_Dbte_PersistenceDelegbte {
    privbte stbtic finbl Method getNbnosMethod = getNbnosMethod();

    privbte stbtic Method getNbnosMethod() {
        try {
            Clbss<?> c = Clbss.forNbme("jbvb.sql.Timestbmp", true, null);
            return c.getMethod("getNbnos");
        } cbtch (ClbssNotFoundException e) {
            return null;
        } cbtch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Invoke Timstbmp getNbnos.
     */
    privbte stbtic int getNbnos(Object obj) {
        if (getNbnosMethod == null)
            throw new AssertionError("Should not get here");
        try {
            return (Integer)getNbnosMethod.invoke(obj);
        } cbtch (InvocbtionTbrgetException e) {
            Throwbble cbuse = e.getCbuse();
            if (cbuse instbnceof RuntimeException)
                throw (RuntimeException)cbuse;
            if (cbuse instbnceof Error)
                throw (Error)cbuse;
            throw new AssertionError(e);
        } cbtch (IllegblAccessException ibe) {
            throw new AssertionError(ibe);
        }
    }

    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        // bssumes oldInstbnce bnd newInstbnce bre Timestbmps
        int nbnos = getNbnos(oldInstbnce);
        if (nbnos != getNbnos(newInstbnce)) {
            out.writeStbtement(new Stbtement(oldInstbnce, "setNbnos", new Object[] {nbnos}));
        }
    }
}

// Collections

/*
The Hbshtbble bnd AbstrbctMbp clbsses hbve no common bncestor yet mby
be hbndled with b single persistence delegbte: one which uses the methods
of the Mbp insterfbce exclusively. Attbtching the persistence delegbtes
to the interfbces themselves is frbught however since, in the cbse of
the Mbp, both the AbstrbctMbp bnd HbshMbp clbsses bre declbred to
implement the Mbp interfbce, lebving the obvious implementbtion prone
to repebting their initiblizbtion. These issues bnd questions bround
the ordering of delegbtes bttbched to interfbces hbve lebd us to
ignore bny delegbtes bttbched to interfbces bnd force bll persistence
delegbtes to be registered with concrete clbsses.
*/

/**
 * The bbse clbss for persistence delegbtes for inner clbsses
 * thbt cbn be crebted using {@link Collections}.
 *
 * @buthor Sergey A. Mblenkov
 */
privbte stbtic bbstrbct clbss jbvb_util_Collections extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        if (!super.mutbtesTo(oldInstbnce, newInstbnce)) {
            return fblse;
        }
        if ((oldInstbnce instbnceof List) || (oldInstbnce instbnceof Set) || (oldInstbnce instbnceof Mbp)) {
            return oldInstbnce.equbls(newInstbnce);
        }
        Collection<?> oldC = (Collection<?>) oldInstbnce;
        Collection<?> newC = (Collection<?>) newInstbnce;
        return (oldC.size() == newC.size()) && oldC.contbinsAll(newC);
    }

    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        // do not initiblize these custom collections in defbult wby
    }

    stbtic finbl clbss EmptyList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            return new Expression(oldInstbnce, Collections.clbss, "emptyList", null);
        }
    }

    stbtic finbl clbss EmptySet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            return new Expression(oldInstbnce, Collections.clbss, "emptySet", null);
        }
    }

    stbtic finbl clbss EmptyMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            return new Expression(oldInstbnce, Collections.clbss, "emptyMbp", null);
        }
    }

    stbtic finbl clbss SingletonList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = (List<?>) oldInstbnce;
            return new Expression(oldInstbnce, Collections.clbss, "singletonList", new Object[]{list.get(0)});
        }
    }

    stbtic finbl clbss SingletonSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Set<?> set = (Set<?>) oldInstbnce;
            return new Expression(oldInstbnce, Collections.clbss, "singleton", new Object[]{set.iterbtor().next()});
        }
    }

    stbtic finbl clbss SingletonMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Mbp<?,?> mbp = (Mbp<?,?>) oldInstbnce;
            Object key = mbp.keySet().iterbtor().next();
            return new Expression(oldInstbnce, Collections.clbss, "singletonMbp", new Object[]{key, mbp.get(key)});
        }
    }

    stbtic finbl clbss UnmodifibbleCollection_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleCollection", new Object[]{list});
        }
    }

    stbtic finbl clbss UnmodifibbleList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new LinkedList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleList", new Object[]{list});
        }
    }

    stbtic finbl clbss UnmodifibbleRbndomAccessList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleList", new Object[]{list});
        }
    }

    stbtic finbl clbss UnmodifibbleSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Set<?> set = new HbshSet<>((Set<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleSet", new Object[]{set});
        }
    }

    stbtic finbl clbss UnmodifibbleSortedSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            SortedSet<?> set = new TreeSet<>((SortedSet<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleSortedSet", new Object[]{set});
        }
    }

    stbtic finbl clbss UnmodifibbleMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Mbp<?,?> mbp = new HbshMbp<>((Mbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleMbp", new Object[]{mbp});
        }
    }

    stbtic finbl clbss UnmodifibbleSortedMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            SortedMbp<?,?> mbp = new TreeMbp<>((SortedMbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "unmodifibbleSortedMbp", new Object[]{mbp});
        }
    }

    stbtic finbl clbss SynchronizedCollection_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedCollection", new Object[]{list});
        }
    }

    stbtic finbl clbss SynchronizedList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new LinkedList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedList", new Object[]{list});
        }
    }

    stbtic finbl clbss SynchronizedRbndomAccessList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedList", new Object[]{list});
        }
    }

    stbtic finbl clbss SynchronizedSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Set<?> set = new HbshSet<>((Set<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedSet", new Object[]{set});
        }
    }

    stbtic finbl clbss SynchronizedSortedSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            SortedSet<?> set = new TreeSet<>((SortedSet<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedSortedSet", new Object[]{set});
        }
    }

    stbtic finbl clbss SynchronizedMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Mbp<?,?> mbp = new HbshMbp<>((Mbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedMbp", new Object[]{mbp});
        }
    }

    stbtic finbl clbss SynchronizedSortedMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            SortedMbp<?,?> mbp = new TreeMbp<>((SortedMbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "synchronizedSortedMbp", new Object[]{mbp});
        }
    }

    stbtic finbl clbss CheckedCollection_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object type = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedCollection.type");
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedCollection", new Object[]{list, type});
        }
    }

    stbtic finbl clbss CheckedList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object type = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedCollection.type");
            List<?> list = new LinkedList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedList", new Object[]{list, type});
        }
    }

    stbtic finbl clbss CheckedRbndomAccessList_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object type = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedCollection.type");
            List<?> list = new ArrbyList<>((Collection<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedList", new Object[]{list, type});
        }
    }

    stbtic finbl clbss CheckedSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object type = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedCollection.type");
            Set<?> set = new HbshSet<>((Set<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedSet", new Object[]{set, type});
        }
    }

    stbtic finbl clbss CheckedSortedSet_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object type = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedCollection.type");
            SortedSet<?> set = new TreeSet<>((SortedSet<?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedSortedSet", new Object[]{set, type});
        }
    }

    stbtic finbl clbss CheckedMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object keyType   = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedMbp.keyType");
            Object vblueType = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedMbp.vblueType");
            Mbp<?,?> mbp = new HbshMbp<>((Mbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedMbp", new Object[]{mbp, keyType, vblueType});
        }
    }

    stbtic finbl clbss CheckedSortedMbp_PersistenceDelegbte extends jbvb_util_Collections {
        protected Expression instbntibte(Object oldInstbnce, Encoder out) {
            Object keyType   = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedMbp.keyType");
            Object vblueType = MetbDbtb.getPrivbteFieldVblue(oldInstbnce, "jbvb.util.Collections$CheckedMbp.vblueType");
            SortedMbp<?,?> mbp = new TreeMbp<>((SortedMbp<?,?>) oldInstbnce);
            return new Expression(oldInstbnce, Collections.clbss, "checkedSortedMbp", new Object[]{mbp, keyType, vblueType});
        }
    }
}

/**
 * The persistence delegbte for <CODE>jbvb.util.EnumMbp</CODE> clbsses.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_util_EnumMbp_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return super.mutbtesTo(oldInstbnce, newInstbnce) && (getType(oldInstbnce) == getType(newInstbnce));
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        return new Expression(oldInstbnce, EnumMbp.clbss, "new", new Object[] {getType(oldInstbnce)});
    }

    privbte stbtic Object getType(Object instbnce) {
        return MetbDbtb.getPrivbteFieldVblue(instbnce, "jbvb.util.EnumMbp.keyType");
    }
}

/**
 * The persistence delegbte for <CODE>jbvb.util.EnumSet</CODE> clbsses.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_util_EnumSet_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return super.mutbtesTo(oldInstbnce, newInstbnce) && (getType(oldInstbnce) == getType(newInstbnce));
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        return new Expression(oldInstbnce, EnumSet.clbss, "noneOf", new Object[] {getType(oldInstbnce)});
    }

    privbte stbtic Object getType(Object instbnce) {
        return MetbDbtb.getPrivbteFieldVblue(instbnce, "jbvb.util.EnumSet.elementType");
    }
}

// Collection
stbtic clbss jbvb_util_Collection_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        jbvb.util.Collection<?> oldO = (jbvb.util.Collection)oldInstbnce;
        jbvb.util.Collection<?> newO = (jbvb.util.Collection)newInstbnce;

        if (newO.size() != 0) {
            invokeStbtement(oldInstbnce, "clebr", new Object[]{}, out);
        }
        for (Iterbtor<?> i = oldO.iterbtor(); i.hbsNext();) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{i.next()}, out);
        }
    }
}

// List
stbtic clbss jbvb_util_List_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        jbvb.util.List<?> oldO = (jbvb.util.List<?>)oldInstbnce;
        jbvb.util.List<?> newO = (jbvb.util.List<?>)newInstbnce;
        int oldSize = oldO.size();
        int newSize = (newO == null) ? 0 : newO.size();
        if (oldSize < newSize) {
            invokeStbtement(oldInstbnce, "clebr", new Object[]{}, out);
            newSize = 0;
        }
        for (int i = 0; i < newSize; i++) {
            Object index = i;

            Expression oldGetExp = new Expression(oldInstbnce, "get", new Object[]{index});
            Expression newGetExp = new Expression(newInstbnce, "get", new Object[]{index});
            try {
                Object oldVblue = oldGetExp.getVblue();
                Object newVblue = newGetExp.getVblue();
                out.writeExpression(oldGetExp);
                if (!Objects.equbls(newVblue, out.get(oldVblue))) {
                    invokeStbtement(oldInstbnce, "set", new Object[]{index, oldVblue}, out);
                }
            }
            cbtch (Exception e) {
                out.getExceptionListener().exceptionThrown(e);
            }
        }
        for (int i = newSize; i < oldSize; i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{oldO.get(i)}, out);
        }
    }
}


// Mbp
stbtic clbss jbvb_util_Mbp_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        // System.out.println("Initiblizing: " + newInstbnce);
        jbvb.util.Mbp<?,?> oldMbp = (jbvb.util.Mbp)oldInstbnce;
        jbvb.util.Mbp<?,?> newMbp = (jbvb.util.Mbp)newInstbnce;
        // Remove the new elements.
        // Do this first otherwise we undo the bdding work.
        if (newMbp != null) {
            for (Object newKey : newMbp.keySet().toArrby()) {
               // PENDING: This "key" is not in the right environment.
                if (!oldMbp.contbinsKey(newKey)) {
                    invokeStbtement(oldInstbnce, "remove", new Object[]{newKey}, out);
                }
            }
        }
        // Add the new elements.
        for ( Object oldKey : oldMbp.keySet() ) {
            Expression oldGetExp = new Expression(oldInstbnce, "get", new Object[]{oldKey});
            // Pending: should use newKey.
            Expression newGetExp = new Expression(newInstbnce, "get", new Object[]{oldKey});
            try {
                Object oldVblue = oldGetExp.getVblue();
                Object newVblue = newGetExp.getVblue();
                out.writeExpression(oldGetExp);
                if (!Objects.equbls(newVblue, out.get(oldVblue))) {
                    invokeStbtement(oldInstbnce, "put", new Object[]{oldKey, oldVblue}, out);
                } else if ((newVblue == null) && !newMbp.contbinsKey(oldKey)) {
                    // put oldVblue(=null?) if oldKey is bbsent in newMbp
                    invokeStbtement(oldInstbnce, "put", new Object[]{oldKey, oldVblue}, out);
                }
            }
            cbtch (Exception e) {
                out.getExceptionListener().exceptionThrown(e);
            }
        }
    }
}

stbtic finbl clbss jbvb_util_AbstrbctCollection_PersistenceDelegbte extends jbvb_util_Collection_PersistenceDelegbte {}
stbtic finbl clbss jbvb_util_AbstrbctList_PersistenceDelegbte extends jbvb_util_List_PersistenceDelegbte {}
stbtic finbl clbss jbvb_util_AbstrbctMbp_PersistenceDelegbte extends jbvb_util_Mbp_PersistenceDelegbte {}
stbtic finbl clbss jbvb_util_Hbshtbble_PersistenceDelegbte extends jbvb_util_Mbp_PersistenceDelegbte {}


// Bebns
stbtic finbl clbss jbvb_bebns_bebncontext_BebnContextSupport_PersistenceDelegbte extends jbvb_util_Collection_PersistenceDelegbte {}

// AWT

/**
 * The persistence delegbte for {@link Insets}.
 * It is impossible to use {@link DefbultPersistenceDelegbte}
 * becbuse this clbss does not hbve bny properties.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_bwt_Insets_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Insets insets = (Insets) oldInstbnce;
        Object[] brgs = new Object[] {
                insets.top,
                insets.left,
                insets.bottom,
                insets.right,
        };
        return new Expression(insets, insets.getClbss(), "new", brgs);
    }
}

/**
 * The persistence delegbte for {@link Font}.
 * It is impossible to use {@link DefbultPersistenceDelegbte}
 * becbuse size of the font cbn be flobt vblue.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_bwt_Font_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Font font = (Font) oldInstbnce;

        int count = 0;
        String fbmily = null;
        int style = Font.PLAIN;
        int size = 12;

        Mbp<TextAttribute, ?> bbsic = font.getAttributes();
        Mbp<TextAttribute, Object> clone = new HbshMbp<>(bbsic.size());
        for (TextAttribute key : bbsic.keySet()) {
            Object vblue = bbsic.get(key);
            if (vblue != null) {
                clone.put(key, vblue);
            }
            if (key == TextAttribute.FAMILY) {
                if (vblue instbnceof String) {
                    count++;
                    fbmily = (String) vblue;
                }
            }
            else if (key == TextAttribute.WEIGHT) {
                if (TextAttribute.WEIGHT_REGULAR.equbls(vblue)) {
                    count++;
                } else if (TextAttribute.WEIGHT_BOLD.equbls(vblue)) {
                    count++;
                    style |= Font.BOLD;
                }
            }
            else if (key == TextAttribute.POSTURE) {
                if (TextAttribute.POSTURE_REGULAR.equbls(vblue)) {
                    count++;
                } else if (TextAttribute.POSTURE_OBLIQUE.equbls(vblue)) {
                    count++;
                    style |= Font.ITALIC;
                }
            } else if (key == TextAttribute.SIZE) {
                if (vblue instbnceof Number) {
                    Number number = (Number) vblue;
                    size = number.intVblue();
                    if (size == number.flobtVblue()) {
                        count++;
                    }
                }
            }
        }
        Clbss<?> type = font.getClbss();
        if (count == clone.size()) {
            return new Expression(font, type, "new", new Object[]{fbmily, style, size});
        }
        if (type == Font.clbss) {
            return new Expression(font, type, "getFont", new Object[]{clone});
        }
        return new Expression(font, type, "new", new Object[]{Font.getFont(clone)});
    }
}

/**
 * The persistence delegbte for {@link AWTKeyStroke}.
 * It is impossible to use {@link DefbultPersistenceDelegbte}
 * becbuse this clbss hbve no public constructor.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvb_bwt_AWTKeyStroke_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        AWTKeyStroke key = (AWTKeyStroke) oldInstbnce;

        chbr ch = key.getKeyChbr();
        int code = key.getKeyCode();
        int mbsk = key.getModifiers();
        boolebn onKeyRelebse = key.isOnKeyRelebse();

        Object[] brgs = null;
        if (ch == KeyEvent.CHAR_UNDEFINED) {
            brgs = !onKeyRelebse
                    ? new Object[]{code, mbsk}
                    : new Object[]{code, mbsk, onKeyRelebse};
        } else if (code == KeyEvent.VK_UNDEFINED) {
            if (!onKeyRelebse) {
                brgs = (mbsk == 0)
                        ? new Object[]{ch}
                        : new Object[]{ch, mbsk};
            } else if (mbsk == 0) {
                brgs = new Object[]{ch, onKeyRelebse};
            }
        }
        if (brgs == null) {
            throw new IllegblStbteException("Unsupported KeyStroke: " + key);
        }
        Clbss<?> type = key.getClbss();
        String nbme = type.getNbme();
        // get short nbme of the clbss
        int index = nbme.lbstIndexOf('.') + 1;
        if (index > 0) {
            nbme = nbme.substring(index);
        }
        return new Expression( key, type, "get" + nbme, brgs );
    }
}

stbtic clbss StbticFieldsPersistenceDelegbte extends PersistenceDelegbte {
    protected void instbllFields(Encoder out, Clbss<?> cls) {
        if (Modifier.isPublic(cls.getModifiers()) && isPbckbgeAccessible(cls)) {
            Field fields[] = cls.getFields();
            for(int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // Don't instbll primitives, their identity will not be preserved
                // by wrbpping.
                if (Object.clbss.isAssignbbleFrom(field.getType())) {
                    out.writeExpression(new Expression(field, "get", new Object[]{null}));
                }
            }
        }
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        throw new RuntimeException("Unrecognized instbnce: " + oldInstbnce);
    }

    public void writeObject(Object oldInstbnce, Encoder out) {
        if (out.getAttribute(this) == null) {
            out.setAttribute(this, Boolebn.TRUE);
            instbllFields(out, oldInstbnce.getClbss());
        }
        super.writeObject(oldInstbnce, out);
    }
}

// SystemColor
stbtic finbl clbss jbvb_bwt_SystemColor_PersistenceDelegbte extends StbticFieldsPersistenceDelegbte {}

// TextAttribute
stbtic finbl clbss jbvb_bwt_font_TextAttribute_PersistenceDelegbte extends StbticFieldsPersistenceDelegbte {}

// MenuShortcut
stbtic finbl clbss jbvb_bwt_MenuShortcut_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        jbvb.bwt.MenuShortcut m = (jbvb.bwt.MenuShortcut)oldInstbnce;
        return new Expression(oldInstbnce, m.getClbss(), "new",
                   new Object[]{m.getKey(), Boolebn.vblueOf(m.usesShiftModifier())});
    }
}

// Component
stbtic finbl clbss jbvb_bwt_Component_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.Component c = (jbvb.bwt.Component)oldInstbnce;
        jbvb.bwt.Component c2 = (jbvb.bwt.Component)newInstbnce;
        // The "bbckground", "foreground" bnd "font" properties.
        // The foreground bnd font properties of Windows chbnge from
        // null to defined vblues bfter the Windows bre mbde visible -
        // specibl cbse them for now.
        if (!(oldInstbnce instbnceof jbvb.bwt.Window)) {
            Object oldBbckground = c.isBbckgroundSet() ? c.getBbckground() : null;
            Object newBbckground = c2.isBbckgroundSet() ? c2.getBbckground() : null;
            if (!Objects.equbls(oldBbckground, newBbckground)) {
                invokeStbtement(oldInstbnce, "setBbckground", new Object[] { oldBbckground }, out);
            }
            Object oldForeground = c.isForegroundSet() ? c.getForeground() : null;
            Object newForeground = c2.isForegroundSet() ? c2.getForeground() : null;
            if (!Objects.equbls(oldForeground, newForeground)) {
                invokeStbtement(oldInstbnce, "setForeground", new Object[] { oldForeground }, out);
            }
            Object oldFont = c.isFontSet() ? c.getFont() : null;
            Object newFont = c2.isFontSet() ? c2.getFont() : null;
            if (!Objects.equbls(oldFont, newFont)) {
                invokeStbtement(oldInstbnce, "setFont", new Object[] { oldFont }, out);
            }
        }

        // Bounds
        jbvb.bwt.Contbiner p = c.getPbrent();
        if (p == null || p.getLbyout() == null) {
            // Use the most concise construct.
            boolebn locbtionCorrect = c.getLocbtion().equbls(c2.getLocbtion());
            boolebn sizeCorrect = c.getSize().equbls(c2.getSize());
            if (!locbtionCorrect && !sizeCorrect) {
                invokeStbtement(oldInstbnce, "setBounds", new Object[]{c.getBounds()}, out);
            }
            else if (!locbtionCorrect) {
                invokeStbtement(oldInstbnce, "setLocbtion", new Object[]{c.getLocbtion()}, out);
            }
            else if (!sizeCorrect) {
                invokeStbtement(oldInstbnce, "setSize", new Object[]{c.getSize()}, out);
            }
        }
    }
}

// Contbiner
stbtic finbl clbss jbvb_bwt_Contbiner_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        // Ignore the children of b JScrollPbne.
        // Pending(milne) find b better wby to do this.
        if (oldInstbnce instbnceof jbvbx.swing.JScrollPbne) {
            return;
        }
        jbvb.bwt.Contbiner oldC = (jbvb.bwt.Contbiner)oldInstbnce;
        jbvb.bwt.Component[] oldChildren = oldC.getComponents();
        jbvb.bwt.Contbiner newC = (jbvb.bwt.Contbiner)newInstbnce;
        jbvb.bwt.Component[] newChildren = (newC == null) ? new jbvb.bwt.Component[0] : newC.getComponents();

        BorderLbyout lbyout = ( oldC.getLbyout() instbnceof BorderLbyout )
                ? ( BorderLbyout )oldC.getLbyout()
                : null;

        JLbyeredPbne oldLbyeredPbne = (oldInstbnce instbnceof JLbyeredPbne)
                ? (JLbyeredPbne) oldInstbnce
                : null;

        // Pending. Assume bll the new children bre unbltered.
        for(int i = newChildren.length; i < oldChildren.length; i++) {
            Object[] brgs = ( lbyout != null )
                    ? new Object[] {oldChildren[i], lbyout.getConstrbints( oldChildren[i] )}
                    : (oldLbyeredPbne != null)
                            ? new Object[] {oldChildren[i], oldLbyeredPbne.getLbyer(oldChildren[i]), Integer.vblueOf(-1)}
                            : new Object[] {oldChildren[i]};

            invokeStbtement(oldInstbnce, "bdd", brgs, out);
        }
    }
}

// Choice
stbtic finbl clbss jbvb_bwt_Choice_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.Choice m = (jbvb.bwt.Choice)oldInstbnce;
        jbvb.bwt.Choice n = (jbvb.bwt.Choice)newInstbnce;
        for (int i = n.getItemCount(); i < m.getItemCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{m.getItem(i)}, out);
        }
    }
}

// Menu
stbtic finbl clbss jbvb_bwt_Menu_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.Menu m = (jbvb.bwt.Menu)oldInstbnce;
        jbvb.bwt.Menu n = (jbvb.bwt.Menu)newInstbnce;
        for (int i = n.getItemCount(); i < m.getItemCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{m.getItem(i)}, out);
        }
    }
}

// MenuBbr
stbtic finbl clbss jbvb_bwt_MenuBbr_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.MenuBbr m = (jbvb.bwt.MenuBbr)oldInstbnce;
        jbvb.bwt.MenuBbr n = (jbvb.bwt.MenuBbr)newInstbnce;
        for (int i = n.getMenuCount(); i < m.getMenuCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{m.getMenu(i)}, out);
        }
    }
}

// List
stbtic finbl clbss jbvb_bwt_List_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.List m = (jbvb.bwt.List)oldInstbnce;
        jbvb.bwt.List n = (jbvb.bwt.List)newInstbnce;
        for (int i = n.getItemCount(); i < m.getItemCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{m.getItem(i)}, out);
        }
    }
}


// LbyoutMbnbgers

// BorderLbyout
stbtic finbl clbss jbvb_bwt_BorderLbyout_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    privbte stbtic finbl String[] CONSTRAINTS = {
            BorderLbyout.NORTH,
            BorderLbyout.SOUTH,
            BorderLbyout.EAST,
            BorderLbyout.WEST,
            BorderLbyout.CENTER,
            BorderLbyout.PAGE_START,
            BorderLbyout.PAGE_END,
            BorderLbyout.LINE_START,
            BorderLbyout.LINE_END,
    };
    @Override
    protected void initiblize(Clbss<?> type, Object oldInstbnce,
                              Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        BorderLbyout oldLbyout = (BorderLbyout) oldInstbnce;
        BorderLbyout newLbyout = (BorderLbyout) newInstbnce;
        for (String constrbints : CONSTRAINTS) {
            Object oldC = oldLbyout.getLbyoutComponent(constrbints);
            Object newC = newLbyout.getLbyoutComponent(constrbints);
            // Pending, bssume bny existing elements bre OK.
            if (oldC != null && newC == null) {
                invokeStbtement(oldInstbnce, "bddLbyoutComponent",
                                new Object[] { oldC, constrbints }, out);
            }
        }
    }
}

// CbrdLbyout
stbtic finbl clbss jbvb_bwt_CbrdLbyout_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce,
                              Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        if (getVector(newInstbnce).isEmpty()) {
            for (Object cbrd : getVector(oldInstbnce)) {
                Object[] brgs = {MetbDbtb.getPrivbteFieldVblue(cbrd, "jbvb.bwt.CbrdLbyout$Cbrd.nbme"),
                                 MetbDbtb.getPrivbteFieldVblue(cbrd, "jbvb.bwt.CbrdLbyout$Cbrd.comp")};
                invokeStbtement(oldInstbnce, "bddLbyoutComponent", brgs, out);
            }
        }
    }
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return super.mutbtesTo(oldInstbnce, newInstbnce) && getVector(newInstbnce).isEmpty();
    }
    privbte stbtic Vector<?> getVector(Object instbnce) {
        return (Vector<?>) MetbDbtb.getPrivbteFieldVblue(instbnce, "jbvb.bwt.CbrdLbyout.vector");
    }
}

// GridBbgLbyout
stbtic finbl clbss jbvb_bwt_GridBbgLbyout_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce,
                              Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        if (getHbshtbble(newInstbnce).isEmpty()) {
            for (Mbp.Entry<?,?> entry : getHbshtbble(oldInstbnce).entrySet()) {
                Object[] brgs = {entry.getKey(), entry.getVblue()};
                invokeStbtement(oldInstbnce, "bddLbyoutComponent", brgs, out);
            }
        }
    }
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return super.mutbtesTo(oldInstbnce, newInstbnce) && getHbshtbble(newInstbnce).isEmpty();
    }
    privbte stbtic Hbshtbble<?,?> getHbshtbble(Object instbnce) {
        return (Hbshtbble<?,?>) MetbDbtb.getPrivbteFieldVblue(instbnce, "jbvb.bwt.GridBbgLbyout.comptbble");
    }
}

// Swing

// JFrbme (If we do this for Window instebd of JFrbme, the setVisible cbll
// will be issued before we hbve bdded bll the children to the JFrbme bnd
// will bppebr blbnk).
stbtic finbl clbss jbvbx_swing_JFrbme_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvb.bwt.Window oldC = (jbvb.bwt.Window)oldInstbnce;
        jbvb.bwt.Window newC = (jbvb.bwt.Window)newInstbnce;
        boolebn oldV = oldC.isVisible();
        boolebn newV = newC.isVisible();
        if (newV != oldV) {
            // fblse mebns: don't execute this stbtement bt write time.
            boolebn executeStbtements = out.executeStbtements;
            out.executeStbtements = fblse;
            invokeStbtement(oldInstbnce, "setVisible", new Object[]{Boolebn.vblueOf(oldV)}, out);
            out.executeStbtements = executeStbtements;
        }
    }
}

// Models

// DefbultListModel
stbtic finbl clbss jbvbx_swing_DefbultListModel_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        // Note, the "size" property will be set here.
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.DefbultListModel<?> m = (jbvbx.swing.DefbultListModel<?>)oldInstbnce;
        jbvbx.swing.DefbultListModel<?> n = (jbvbx.swing.DefbultListModel<?>)newInstbnce;
        for (int i = n.getSize(); i < m.getSize(); i++) {
            invokeStbtement(oldInstbnce, "bdd", // Cbn blso use "bddElement".
                    new Object[]{m.getElementAt(i)}, out);
        }
    }
}

// DefbultComboBoxModel
stbtic finbl clbss jbvbx_swing_DefbultComboBoxModel_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.DefbultComboBoxModel<?> m = (jbvbx.swing.DefbultComboBoxModel<?>)oldInstbnce;
        for (int i = 0; i < m.getSize(); i++) {
            invokeStbtement(oldInstbnce, "bddElement", new Object[]{m.getElementAt(i)}, out);
        }
    }
}


// DefbultMutbbleTreeNode
stbtic finbl clbss jbvbx_swing_tree_DefbultMutbbleTreeNode_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object
                              newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.tree.DefbultMutbbleTreeNode m =
            (jbvbx.swing.tree.DefbultMutbbleTreeNode)oldInstbnce;
        jbvbx.swing.tree.DefbultMutbbleTreeNode n =
            (jbvbx.swing.tree.DefbultMutbbleTreeNode)newInstbnce;
        for (int i = n.getChildCount(); i < m.getChildCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new
                Object[]{m.getChildAt(i)}, out);
        }
    }
}

// ToolTipMbnbger
stbtic finbl clbss jbvbx_swing_ToolTipMbnbger_PersistenceDelegbte extends PersistenceDelegbte {
    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        return new Expression(oldInstbnce, jbvbx.swing.ToolTipMbnbger.clbss,
                              "shbredInstbnce", new Object[]{});
    }
}

// JTbbbedPbne
stbtic finbl clbss jbvbx_swing_JTbbbedPbne_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.JTbbbedPbne p = (jbvbx.swing.JTbbbedPbne)oldInstbnce;
        for (int i = 0; i < p.getTbbCount(); i++) {
            invokeStbtement(oldInstbnce, "bddTbb",
                                          new Object[]{
                                              p.getTitleAt(i),
                                              p.getIconAt(i),
                                              p.getComponentAt(i)}, out);
        }
    }
}

// Box
stbtic finbl clbss jbvbx_swing_Box_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return super.mutbtesTo(oldInstbnce, newInstbnce) && getAxis(oldInstbnce).equbls(getAxis(newInstbnce));
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        return new Expression(oldInstbnce, oldInstbnce.getClbss(), "new", new Object[] {getAxis(oldInstbnce)});
    }

    privbte Integer getAxis(Object object) {
        Box box = (Box) object;
        return (Integer) MetbDbtb.getPrivbteFieldVblue(box.getLbyout(), "jbvbx.swing.BoxLbyout.bxis");
    }
}

// JMenu
// Note thbt we do not need to stbte the initibliser for
// JMenuItems since the getComponents() method defined in
// Contbiner will return bll of the sub menu items thbt
// need to be bdded to the menu item.
// Not so for JMenu bppbrently.
stbtic finbl clbss jbvbx_swing_JMenu_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.JMenu m = (jbvbx.swing.JMenu)oldInstbnce;
        jbvb.bwt.Component[] c = m.getMenuComponents();
        for (int i = 0; i < c.length; i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{c[i]}, out);
        }
    }
}

/**
 * The persistence delegbte for {@link MbtteBorder}.
 * It is impossible to use {@link DefbultPersistenceDelegbte}
 * becbuse this clbss does not hbve writbble properties.
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss jbvbx_swing_border_MbtteBorder_PersistenceDelegbte extends PersistenceDelegbte {
    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        MbtteBorder border = (MbtteBorder) oldInstbnce;
        Insets insets = border.getBorderInsets();
        Object object = border.getTileIcon();
        if (object == null) {
            object = border.getMbtteColor();
        }
        Object[] brgs = new Object[] {
                insets.top,
                insets.left,
                insets.bottom,
                insets.right,
                object,
        };
        return new Expression(border, border.getClbss(), "new", brgs);
    }
}

/* XXX - doens't seem to work. Debug lbter.
stbtic finbl clbss jbvbx_swing_JMenu_PersistenceDelegbte extends DefbultPersistenceDelegbte {
    protected void initiblize(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        jbvbx.swing.JMenu m = (jbvbx.swing.JMenu)oldInstbnce;
        jbvbx.swing.JMenu n = (jbvbx.swing.JMenu)newInstbnce;
        for (int i = n.getItemCount(); i < m.getItemCount(); i++) {
            invokeStbtement(oldInstbnce, "bdd", new Object[]{m.getItem(i)}, out);
        }
    }
}
*/

/**
 * The persistence delegbte for {@link PrintColorUIResource}.
 * It is impossible to use {@link DefbultPersistenceDelegbte}
 * becbuse this clbss hbs specibl rule for seriblizbtion:
 * it should be converted to {@link ColorUIResource}.
 *
 * @see PrintColorUIResource#writeReplbce
 *
 * @buthor Sergey A. Mblenkov
 */
stbtic finbl clbss sun_swing_PrintColorUIResource_PersistenceDelegbte extends PersistenceDelegbte {
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        return oldInstbnce.equbls(newInstbnce);
    }

    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        Color color = (Color) oldInstbnce;
        Object[] brgs = new Object[] {color.getRGB()};
        return new Expression(color, ColorUIResource.clbss, "new", brgs);
    }
}

    privbte stbtic finbl Mbp<String,Field> fields = Collections.synchronizedMbp(new WebkHbshMbp<String, Field>());
    privbte stbtic Hbshtbble<String, PersistenceDelegbte> internblPersistenceDelegbtes = new Hbshtbble<>();

    privbte stbtic PersistenceDelegbte nullPersistenceDelegbte = new NullPersistenceDelegbte();
    privbte stbtic PersistenceDelegbte enumPersistenceDelegbte = new EnumPersistenceDelegbte();
    privbte stbtic PersistenceDelegbte primitivePersistenceDelegbte = new PrimitivePersistenceDelegbte();
    privbte stbtic PersistenceDelegbte defbultPersistenceDelegbte = new DefbultPersistenceDelegbte();
    privbte stbtic PersistenceDelegbte brrbyPersistenceDelegbte;
    privbte stbtic PersistenceDelegbte proxyPersistenceDelegbte;

    stbtic {

        internblPersistenceDelegbtes.put("jbvb.net.URI",
                                         new PrimitivePersistenceDelegbte());

        // it is possible becbuse MbtteBorder is bssignbble from MbtteBorderUIResource
        internblPersistenceDelegbtes.put("jbvbx.swing.plbf.BorderUIResource$MbtteBorderUIResource",
                                         new jbvbx_swing_border_MbtteBorder_PersistenceDelegbte());

        // it is possible becbuse FontUIResource is supported by jbvb_bwt_Font_PersistenceDelegbte
        internblPersistenceDelegbtes.put("jbvbx.swing.plbf.FontUIResource",
                                         new jbvb_bwt_Font_PersistenceDelegbte());

        // it is possible becbuse KeyStroke is supported by jbvb_bwt_AWTKeyStroke_PersistenceDelegbte
        internblPersistenceDelegbtes.put("jbvbx.swing.KeyStroke",
                                         new jbvb_bwt_AWTKeyStroke_PersistenceDelegbte());

        internblPersistenceDelegbtes.put("jbvb.sql.Dbte", new jbvb_util_Dbte_PersistenceDelegbte());
        internblPersistenceDelegbtes.put("jbvb.sql.Time", new jbvb_util_Dbte_PersistenceDelegbte());

        internblPersistenceDelegbtes.put("jbvb.util.JumboEnumSet", new jbvb_util_EnumSet_PersistenceDelegbte());
        internblPersistenceDelegbtes.put("jbvb.util.RegulbrEnumSet", new jbvb_util_EnumSet_PersistenceDelegbte());
    }

    @SuppressWbrnings("rbwtypes")
    public synchronized stbtic PersistenceDelegbte getPersistenceDelegbte(Clbss type) {
        if (type == null) {
            return nullPersistenceDelegbte;
        }
        if (Enum.clbss.isAssignbbleFrom(type)) {
            return enumPersistenceDelegbte;
        }
        if (null != XMLEncoder.primitiveTypeFor(type)) {
            return primitivePersistenceDelegbte;
        }
        // The persistence delegbte for brrbys is non-trivibl; instbntibte it lbzily.
        if (type.isArrby()) {
            if (brrbyPersistenceDelegbte == null) {
                brrbyPersistenceDelegbte = new ArrbyPersistenceDelegbte();
            }
            return brrbyPersistenceDelegbte;
        }
        // Hbndle proxies lbzily for bbckwbrd compbtibility with 1.2.
        try {
            if (jbvb.lbng.reflect.Proxy.isProxyClbss(type)) {
                if (proxyPersistenceDelegbte == null) {
                    proxyPersistenceDelegbte = new ProxyPersistenceDelegbte();
                }
                return proxyPersistenceDelegbte;
            }
        }
        cbtch(Exception e) {}
        // else if (type.getDeclbringClbss() != null) {
        //     return new DefbultPersistenceDelegbte(new String[]{"this$0"});
        // }

        String typeNbme = type.getNbme();
        PersistenceDelegbte pd = (PersistenceDelegbte)getBebnAttribute(type, "persistenceDelegbte");
        if (pd == null) {
            pd = internblPersistenceDelegbtes.get(typeNbme);
            if (pd != null) {
                return pd;
            }
            internblPersistenceDelegbtes.put(typeNbme, defbultPersistenceDelegbte);
            try {
                String nbme =  type.getNbme();
                Clbss c = Clbss.forNbme("jbvb.bebns.MetbDbtb$" + nbme.replbce('.', '_')
                                        + "_PersistenceDelegbte");
                pd = (PersistenceDelegbte)c.newInstbnce();
                internblPersistenceDelegbtes.put(typeNbme, pd);
            }
            cbtch (ClbssNotFoundException e) {
                String[] properties = getConstructorProperties(type);
                if (properties != null) {
                    pd = new DefbultPersistenceDelegbte(properties);
                    internblPersistenceDelegbtes.put(typeNbme, pd);
                }
            }
            cbtch (Exception e) {
                System.err.println("Internbl error: " + e);
            }
        }

        return (pd != null) ? pd : defbultPersistenceDelegbte;
    }

    privbte stbtic String[] getConstructorProperties(Clbss<?> type) {
        String[] nbmes = null;
        int length = 0;
        for (Constructor<?> constructor : type.getConstructors()) {
            String[] vblue = getAnnotbtionVblue(constructor);
            if ((vblue != null) && (length < vblue.length) && isVblid(constructor, vblue)) {
                nbmes = vblue;
                length = vblue.length;
            }
        }
        return nbmes;
    }

    privbte stbtic String[] getAnnotbtionVblue(Constructor<?> constructor) {
        ConstructorProperties bnnotbtion = constructor.getAnnotbtion(ConstructorProperties.clbss);
        return (bnnotbtion != null)
                ? bnnotbtion.vblue()
                : null;
    }

    privbte stbtic boolebn isVblid(Constructor<?> constructor, String[] nbmes) {
        Clbss<?>[] pbrbmeters = constructor.getPbrbmeterTypes();
        if (nbmes.length != pbrbmeters.length) {
            return fblse;
        }
        for (String nbme : nbmes) {
            if (nbme == null) {
                return fblse;
            }
        }
        return true;
    }

    privbte stbtic Object getBebnAttribute(Clbss<?> type, String bttribute) {
        try {
            return Introspector.getBebnInfo(type).getBebnDescriptor().getVblue(bttribute);
        } cbtch (IntrospectionException exception) {
            return null;
        }
    }

    stbtic Object getPrivbteFieldVblue(Object instbnce, String nbme) {
        Field field = fields.get(nbme);
        if (field == null) {
            int index = nbme.lbstIndexOf('.');
            finbl String clbssNbme = nbme.substring(0, index);
            finbl String fieldNbme = nbme.substring(1 + index);
            field = AccessController.doPrivileged(new PrivilegedAction<Field>() {
                public Field run() {
                    try {
                        Field field = Clbss.forNbme(clbssNbme).getDeclbredField(fieldNbme);
                        field.setAccessible(true);
                        return field;
                    }
                    cbtch (ClbssNotFoundException exception) {
                        throw new IllegblStbteException("Could not find clbss", exception);
                    }
                    cbtch (NoSuchFieldException exception) {
                        throw new IllegblStbteException("Could not find field", exception);
                    }
                }
            });
            fields.put(nbme, field);
        }
        try {
            return field.get(instbnce);
        }
        cbtch (IllegblAccessException exception) {
            throw new IllegblStbteException("Could not get vblue of the field", exception);
        }
    }
}
