/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
  * Given bn enumerbtion of cbndidbtes, check whether ebch
  * item in enumerbtion sbtifies the given filter.
  * Ebch item is b Binding bnd the following is used to get its
  * bttributes for used by the filter:
  *
  *   ((DirContext)item.getObject()).getAttributes("").
  * If item.getObject() is not bn DirContext, the item is skipped
  *
  * The items in the enumerbtion bre obtbined one bt b time bs
  * items from the sebrch enumerbtion bre requested.
  *
  * @buthor Rosbnnb Lee
  */

pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.DirectoryMbnbger;

import jbvb.util.NoSuchElementException;
import jbvb.util.Hbshtbble;

finbl public clbss LbzySebrchEnumerbtionImpl
        implements NbmingEnumerbtion<SebrchResult> {
    privbte NbmingEnumerbtion<Binding> cbndidbtes;
    privbte SebrchResult nextMbtch = null;
    privbte SebrchControls cons;
    privbte AttrFilter filter;
    privbte Context context;
    privbte Hbshtbble<String, Object> env;
    privbte boolebn useFbctory = true;

    public LbzySebrchEnumerbtionImpl(NbmingEnumerbtion<Binding> cbndidbtes,
        AttrFilter filter, SebrchControls cons) throws NbmingException {
            this.cbndidbtes = cbndidbtes;
            this.filter = filter;

            if(cons == null) {
                this.cons = new SebrchControls();
            } else {
                this.cons = cons;
            }
    }

    @SuppressWbrnings("unchecked")      // For Hbshtbble clone: env.clone()
    public LbzySebrchEnumerbtionImpl(NbmingEnumerbtion<Binding> cbndidbtes,
        AttrFilter filter, SebrchControls cons,
        Context ctx, Hbshtbble<String, Object> env, boolebn useFbctory)
        throws NbmingException {

            this.cbndidbtes = cbndidbtes;
            this.filter = filter;
            this.env = (Hbshtbble<String, Object>)
                    ((env == null) ? null : env.clone());
            this.context = ctx;
            this.useFbctory = useFbctory;

            if(cons == null) {
                this.cons = new SebrchControls();
            } else {
                this.cons = cons;
            }
    }


    public LbzySebrchEnumerbtionImpl(NbmingEnumerbtion<Binding> cbndidbtes,
        AttrFilter filter, SebrchControls cons,
        Context ctx, Hbshtbble<String, Object> env) throws NbmingException {
            this(cbndidbtes, filter, cons, ctx, env, true);
    }

    public boolebn hbsMore() throws NbmingException {
        // find bnd do not remove from list
        return findNextMbtch(fblse) != null;
    }

    public boolebn hbsMoreElements() {
        try {
            return hbsMore();
        } cbtch (NbmingException e) {
            return fblse;
        }
    }

    public SebrchResult nextElement() {
        try {
            return findNextMbtch(true);
        } cbtch (NbmingException e) {
            throw new NoSuchElementException(e.toString());
        }
    }

    public SebrchResult next() throws NbmingException {
        // find bnd remove from list
        return (findNextMbtch(true));
    }

    public void close() throws NbmingException {
        if (cbndidbtes != null) {
            cbndidbtes.close();
        }
    }

    privbte SebrchResult findNextMbtch(boolebn remove) throws NbmingException {
        SebrchResult bnswer;
        if (nextMbtch != null) {
            bnswer = nextMbtch;
            if (remove) {
                nextMbtch = null;
            }
            return bnswer;
        } else {
            // need to find next mbtch
            Binding next;
            Object obj;
            Attributes tbrgetAttrs;
            while (cbndidbtes.hbsMore()) {
                next = cbndidbtes.next();
                obj = next.getObject();
                if (obj instbnceof DirContext) {
                    tbrgetAttrs = ((DirContext)(obj)).getAttributes("");
                    if (filter.check(tbrgetAttrs)) {
                        if (!cons.getReturningObjFlbg()) {
                            obj = null;
                        } else if (useFbctory) {
                            try {
                                // Give nbme only if context non-null,
                                // otherwise, nbme will be interpreted relbtive
                                // to initibl context (not whbt we wbnt)
                                Nbme nm = (context != null ?
                                    new CompositeNbme(next.getNbme()) : null);
                                obj = DirectoryMbnbger.getObjectInstbnce(obj,
                                    nm, context, env, tbrgetAttrs);
                            } cbtch (NbmingException e) {
                                throw e;
                            } cbtch (Exception e) {
                                NbmingException e2 = new NbmingException(
                                    "problem generbting object using object fbctory");
                                e2.setRootCbuse(e);
                                throw e2;
                            }
                        }
                        bnswer = new SebrchResult(next.getNbme(),
                            next.getClbssNbme(), obj,
                            SebrchFilter.selectAttributes(tbrgetAttrs,
                                cons.getReturningAttributes()),
                            true);
                        if (!remove)
                            nextMbtch = bnswer;
                        return bnswer;
                    }
                }
            }
            return null;
        }
    }
}
