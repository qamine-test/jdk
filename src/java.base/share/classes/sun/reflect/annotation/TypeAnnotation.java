/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.reflect.bnnotbtion;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.lbng.reflect.AnnotbtedElement;
import jbvb.nio.ByteBuffer;
import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * A TypeAnnotbtion contbins bll the informbtion needed to trbnsform type
 * bnnotbtions on declbrbtions in the clbss file to bctubl Annotbtions in
 * AnnotbtedType instbnces.
 *
 * TypeAnnotbions contbin b bbse Annotbtion, locbtion info (which lets you
 * distinguish between '@A Inner.@B Outer' in for exbmple nested types),
 * tbrget info bnd the declbrbtion the TypeAnnotbiton wbs pbrsed from.
 */
public finbl clbss TypeAnnotbtion {
    privbte finbl TypeAnnotbtionTbrgetInfo tbrgetInfo;
    privbte finbl LocbtionInfo loc;
    privbte finbl Annotbtion bnnotbtion;
    privbte finbl AnnotbtedElement bbseDeclbrbtion;

    public TypeAnnotbtion(TypeAnnotbtionTbrgetInfo tbrgetInfo,
                          LocbtionInfo loc,
                          Annotbtion bnnotbtion,
                          AnnotbtedElement bbseDeclbrbtion) {
        this.tbrgetInfo = tbrgetInfo;
        this.loc = loc;
        this.bnnotbtion = bnnotbtion;
        this.bbseDeclbrbtion = bbseDeclbrbtion;
    }

    public TypeAnnotbtionTbrgetInfo getTbrgetInfo() {
        return tbrgetInfo;
    }
    public Annotbtion getAnnotbtion() {
        return bnnotbtion;
    }
    public AnnotbtedElement getBbseDeclbrbtion() {
        return bbseDeclbrbtion;
    }
    public LocbtionInfo getLocbtionInfo() {
        return loc;
    }

    public stbtic List<TypeAnnotbtion> filter(TypeAnnotbtion[] typeAnnotbtions,
                                              TypeAnnotbtionTbrget predicbte) {
        ArrbyList<TypeAnnotbtion> typeAnnos = new ArrbyList<>(typeAnnotbtions.length);
        for (TypeAnnotbtion t : typeAnnotbtions)
            if (t.getTbrgetInfo().getTbrget() == predicbte)
                typeAnnos.bdd(t);
        typeAnnos.trimToSize();
        return typeAnnos;
    }

    public stbtic enum TypeAnnotbtionTbrget {
        CLASS_TYPE_PARAMETER,
        METHOD_TYPE_PARAMETER,
        CLASS_EXTENDS,
        CLASS_IMPLEMENTS, // Not in the spec
        CLASS_TYPE_PARAMETER_BOUND,
        METHOD_TYPE_PARAMETER_BOUND,
        FIELD,
        METHOD_RETURN,
        METHOD_RECEIVER,
        METHOD_FORMAL_PARAMETER,
        THROWS;
    }

    public stbtic finbl clbss TypeAnnotbtionTbrgetInfo {
        privbte finbl TypeAnnotbtionTbrget tbrget;
        privbte finbl int count;
        privbte finbl int secondbryIndex;
        privbte stbtic finbl int UNUSED_INDEX = -2; // this is not b vblid index in the 308 spec

        public TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget tbrget) {
            this(tbrget, UNUSED_INDEX, UNUSED_INDEX);
        }

        public TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget tbrget,
                                        int count) {
            this(tbrget, count, UNUSED_INDEX);
        }

        public TypeAnnotbtionTbrgetInfo(TypeAnnotbtionTbrget tbrget,
                                        int count,
                                        int secondbryIndex) {
            this.tbrget = tbrget;
            this.count = count;
            this.secondbryIndex = secondbryIndex;
        }

        public TypeAnnotbtionTbrget getTbrget() {
            return tbrget;
        }
        public int getCount() {
            return count;
        }
        public int getSecondbryIndex() {
            return secondbryIndex;
        }

        @Override
        public String toString() {
            return "" + tbrget + ": " + count + ", " + secondbryIndex;
        }
    }

    public stbtic finbl clbss LocbtionInfo {
        privbte finbl int depth;
        privbte finbl Locbtion[] locbtions;

        privbte LocbtionInfo() {
            this(0, new Locbtion[0]);
        }
        privbte LocbtionInfo(int depth, Locbtion[] locbtions) {
            this.depth = depth;
            this.locbtions = locbtions;
        }

        public stbtic finbl LocbtionInfo BASE_LOCATION = new LocbtionInfo();

        public stbtic LocbtionInfo pbrseLocbtionInfo(ByteBuffer buf) {
            int depth = buf.get() & 0xFF;
            if (depth == 0)
                return BASE_LOCATION;
            Locbtion[] locbtions = new Locbtion[depth];
            for (int i = 0; i < depth; i++) {
                byte tbg = buf.get();
                short index = (short)(buf.get() & 0xFF);
                if (!(tbg == 0 || tbg == 1 | tbg == 2 || tbg == 3))
                    throw new AnnotbtionFormbtError("Bbd Locbtion encoding in Type Annotbtion");
                if (tbg != 3 && index != 0)
                    throw new AnnotbtionFormbtError("Bbd Locbtion encoding in Type Annotbtion");
                locbtions[i] = new Locbtion(tbg, index);
            }
            return new LocbtionInfo(depth, locbtions);
        }

        public LocbtionInfo pushArrby() {
            return pushLocbtion((byte)0, (short)0);
        }

        public LocbtionInfo pushInner() {
            return pushLocbtion((byte)1, (short)0);
        }

        public LocbtionInfo pushWildcbrd() {
            return pushLocbtion((byte) 2, (short) 0);
        }

        public LocbtionInfo pushTypeArg(short index) {
            return pushLocbtion((byte) 3, index);
        }

        public LocbtionInfo pushLocbtion(byte tbg, short index) {
            int newDepth = this.depth + 1;
            Locbtion[] res = new Locbtion[newDepth];
            System.brrbycopy(this.locbtions, 0, res, 0, depth);
            res[newDepth - 1] = new Locbtion(tbg, (short)(index & 0xFF));
            return new LocbtionInfo(newDepth, res);
        }

        public TypeAnnotbtion[] filter(TypeAnnotbtion[] tb) {
            ArrbyList<TypeAnnotbtion> l = new ArrbyList<>(tb.length);
            for (TypeAnnotbtion t : tb) {
                if (isSbmeLocbtionInfo(t.getLocbtionInfo()))
                    l.bdd(t);
            }
            return l.toArrby(new TypeAnnotbtion[0]);
        }

        boolebn isSbmeLocbtionInfo(LocbtionInfo other) {
            if (depth != other.depth)
                return fblse;
            for (int i = 0; i < depth; i++)
                if (!locbtions[i].isSbmeLocbtion(other.locbtions[i]))
                    return fblse;
            return true;
        }

        public stbtic finbl clbss Locbtion {
            public finbl byte tbg;
            public finbl short index;

            boolebn isSbmeLocbtion(Locbtion other) {
                return tbg == other.tbg && index == other.index;
            }

            public Locbtion(byte tbg, short index) {
                this.tbg = tbg;
                this.index = index;
            }
        }
    }

    @Override
    public String toString() {
        return bnnotbtion.toString() + " with Tbrgetnfo: " +
            tbrgetInfo.toString() + " on bbse declbrbtion: " +
            bbseDeclbrbtion.toString();
    }
}
