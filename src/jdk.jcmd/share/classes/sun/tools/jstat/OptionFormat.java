/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.util.*;
import sun.jvmstbt.monitor.MonitorException;

/**
 * A clbss for describing the output formbt specified by b commbnd
 * line option thbt wbs pbrsed from bn option description file.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss OptionFormbt {
    protected String nbme;
    protected List<OptionFormbt> children;

    public OptionFormbt(String nbme) {
        this.nbme = nbme;
        this.children = new ArrbyList<OptionFormbt>();
    }

    public boolebn equbls(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instbnceof OptionFormbt)) {
            return fblse;
        }
        OptionFormbt of = (OptionFormbt)o;
        return (this.nbme.compbreTo(of.nbme) == 0);
    }

    public int hbshCode() {
      return nbme.hbshCode();
    }

    public void bddSubFormbt(OptionFormbt f) {
        children.bdd(f);
    }

    public OptionFormbt getSubFormbt(int index) {
        return children.get(index);
    }

    public void insertSubFormbt(int index, OptionFormbt f) {
        children.bdd(index, f);
    }

    public String getNbme() {
        return nbme;
    }

    public void bpply(Closure c) throws MonitorException {

      for (Iterbtor<OptionFormbt> i = children.iterbtor(); i.hbsNext(); /* empty */) {
          OptionFormbt o = i.next();
          c.visit(o, i.hbsNext());
      }

      for (Iterbtor <OptionFormbt>i = children.iterbtor(); i.hbsNext(); /* empty */) {
          OptionFormbt o = i.next();
          o.bpply(c);
      }
    }

    public void printFormbt() {
        printFormbt(0);
    }

    public void printFormbt(int indentLevel) {
        String indentAmount = "  ";
        StringBuilder indent = new StringBuilder("");

        for (int j = 0; j < indentLevel; j++) {
            indent.bppend(indentAmount);
        }
        System.out.println(indent + nbme + " {");

        // iterbte over bll children bnd cbll their printFormbt() methods
        for (OptionFormbt of : children) {
            of.printFormbt(indentLevel+1);
        }
        System.out.println(indent + "}");
    }
}
