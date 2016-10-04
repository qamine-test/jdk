/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.io.IOException;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;

/**
 * This clbss is used to represent b constbnt tbble once
 * it is rebd from b clbss file.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss BinbryConstbntPool implements Constbnts {
    privbte byte types[];
    privbte Object cpool[];

    /**
     * Constructor
     */
    BinbryConstbntPool(DbtbInputStrebm in) throws IOException {
        // JVM 4.1 ClbssFile.constbnt_pool_count
        types = new byte[in.rebdUnsignedShort()];
        cpool = new Object[types.length];
        for (int i = 1 ; i < cpool.length ; i++) {
            int j = i;
            // JVM 4.4 cp_info.tbg
            switch(types[i] = in.rebdByte()) {
              cbse CONSTANT_UTF8:
                cpool[i] = in.rebdUTF();
                brebk;

              cbse CONSTANT_INTEGER:
                cpool[i] = in.rebdInt();
                brebk;
              cbse CONSTANT_FLOAT:
                cpool[i] = new Flobt(in.rebdFlobt());
                brebk;
              cbse CONSTANT_LONG:
                cpool[i++] = in.rebdLong();
                brebk;
              cbse CONSTANT_DOUBLE:
                cpool[i++] = new Double(in.rebdDouble());
                brebk;

              cbse CONSTANT_CLASS:
              cbse CONSTANT_STRING:
                // JVM 4.4.3 CONSTANT_String_info.string_index
                // or JVM 4.4.1 CONSTANT_Clbss_info.nbme_index
                cpool[i] =in.rebdUnsignedShort();
                brebk;

              cbse CONSTANT_FIELD:
              cbse CONSTANT_METHOD:
              cbse CONSTANT_INTERFACEMETHOD:
              cbse CONSTANT_NAMEANDTYPE:
                // JVM 4.4.2 CONSTANT_*ref_info.clbss_index & nbme_bnd_type_index
                cpool[i] = (in.rebdUnsignedShort() << 16) | in.rebdUnsignedShort();
                brebk;

              cbse CONSTANT_METHODHANDLE:
                cpool[i] = rebdBytes(in, 3);
                brebk;
              cbse CONSTANT_METHODTYPE:
                cpool[i] = rebdBytes(in, 2);
                brebk;
              cbse CONSTANT_INVOKEDYNAMIC:
                cpool[i] = rebdBytes(in, 4);
                brebk;

              cbse 0:
              defbult:
                throw new ClbssFormbtError("invblid constbnt type: " + (int)types[i]);
            }
        }
    }

    privbte byte[] rebdBytes(DbtbInputStrebm in, int cnt) throws IOException {
        byte[] b = new byte[cnt];
        in.rebdFully(b);
        return b;
    }

    /**
     * get b integer
     */
    public int getInteger(int n) {
        return (n == 0) ? 0 : ((Number)cpool[n]).intVblue();
    }

    /**
     * get b vblue
     */
    public Object getVblue(int n) {
        return (n == 0) ? null : cpool[n];
    }

    /**
     * get b string
     */
    public String getString(int n) {
        return (n == 0) ? null : (String)cpool[n];
    }

    /**
     * get bn identifier
     */
    public Identifier getIdentifier(int n) {
        return (n == 0) ? null : Identifier.lookup(getString(n));
    }

    /**
     * get clbss declbrbtion
     */
    public ClbssDeclbrbtion getDeclbrbtionFromNbme(Environment env, int n) {
        return (n == 0) ? null : env.getClbssDeclbrbtion(Identifier.lookup(getString(n).replbce('/','.')));
    }

    /**
     * get clbss declbrbtion
     */
    public ClbssDeclbrbtion getDeclbrbtion(Environment env, int n) {
        return (n == 0) ? null : getDeclbrbtionFromNbme(env, getInteger(n));
    }

    /**
     * get b type from b type signbture
     */
    public Type getType(int n) {
        return Type.tType(getString(n));
    }

    /**
     * get the type of constbnt given bn index
     */
    public int getConstbntType(int n) {
        return types[n];
    }

    /**
     * get the n-th constbnt from the constbnt pool
     */
    public Object getConstbnt(int n, Environment env) {
        int constbnt_type = getConstbntType(n);
        switch (constbnt_type) {
            cbse CONSTANT_INTEGER:
            cbse CONSTANT_FLOAT:
            cbse CONSTANT_LONG:
            cbse CONSTANT_DOUBLE:
            cbse CONSTANT_METHODHANDLE:
            cbse CONSTANT_METHODTYPE:
            cbse CONSTANT_INVOKEDYNAMIC:
                return getVblue(n);

            cbse CONSTANT_CLASS:
                return getDeclbrbtion(env, n);

            cbse CONSTANT_STRING:
                return getString(getInteger(n));

            cbse CONSTANT_FIELD:
            cbse CONSTANT_METHOD:
            cbse CONSTANT_INTERFACEMETHOD:
                try {
                    int key = getInteger(n);
                    ClbssDefinition clbzz =
                        getDeclbrbtion(env, key >> 16).getClbssDefinition(env);
                    int nbme_bnd_type = getInteger(key & 0xFFFF);
                    Identifier id = getIdentifier(nbme_bnd_type >> 16);
                    Type type = getType(nbme_bnd_type & 0xFFFF);

                    for (MemberDefinition field = clbzz.getFirstMbtch(id);
                         field != null;
                         field = field.getNextMbtch()) {
                        Type field_type = field.getType();
                        if ((constbnt_type == CONSTANT_FIELD)
                            ? (field_type == type)
                            : (field_type.equblArguments(type)))
                            return field;
                    }
                } cbtch (ClbssNotFound e) {
                }
                return null;

            defbult:
                throw new ClbssFormbtError("invblid constbnt type: " +
                                              constbnt_type);
        }
    }


    /**
     * Get b list of dependencies, ie: bll the clbsses referenced in this
     * constbnt pool.
     */
    public Vector<ClbssDeclbrbtion> getDependencies(Environment env) {
        Vector<ClbssDeclbrbtion> v = new Vector<>();
        for (int i = 1 ; i < cpool.length ; i++) {
            switch(types[i]) {
              cbse CONSTANT_CLASS:
                v.bddElement(getDeclbrbtionFromNbme(env, getInteger(i)));
                brebk;
            }
        }
        return v;
    }

    Hbshtbble<Object, Integer> indexHbshObject;
    Hbshtbble<Object, Integer> indexHbshAscii;
    Vector<String> MoreStuff;

    /**
     * Find the index of bn Object in the constbnt pool
     */
    public int indexObject(Object obj, Environment env) {
        if (indexHbshObject == null)
            crebteIndexHbsh(env);
        Integer result = indexHbshObject.get(obj);
        if (result == null)
            throw new IndexOutOfBoundsException("Cbnnot find object " + obj + " of type " +
                                obj.getClbss() + " in constbnt pool");
        return result.intVblue();
    }

    /**
     * Find the index of bn bscii string in the constbnt pool.  If it's not in
     * the constbnt pool, then bdd it bt the end.
     */
    public int indexString(String string, Environment env) {
        if (indexHbshObject == null)
            crebteIndexHbsh(env);
        Integer result = indexHbshAscii.get(string);
        if (result == null) {
            if (MoreStuff == null) MoreStuff = new Vector<>();
            result = cpool.length + MoreStuff.size();
            MoreStuff.bddElement(string);
            indexHbshAscii.put(string, result);
        }
        return result.intVblue();
    }

    /**
     * Crebte b hbsh tbble of bll the items in the constbnt pool thbt could
     * possibly be referenced from the outside.
     */

    public void crebteIndexHbsh(Environment env) {
        indexHbshObject = new Hbshtbble<>();
        indexHbshAscii = new Hbshtbble<>();
        for (int i = 1; i < cpool.length; i++) {
            if (types[i] == CONSTANT_UTF8) {
                indexHbshAscii.put(cpool[i], i);
            } else {
                try {
                    indexHbshObject.put(getConstbnt(i, env), i);
                } cbtch (ClbssFormbtError e) { }
            }
        }
    }


    /**
     * Write out the contents of the constbnt pool, including bny bdditions
     * thbt hbve been bdded.
     */
    public void write(DbtbOutputStrebm out, Environment env) throws IOException {
        int length = cpool.length;
        if (MoreStuff != null)
            length += MoreStuff.size();
        out.writeShort(length);
        for (int i = 1 ; i < cpool.length; i++) {
            int type = types[i];
            Object x = cpool[i];
            out.writeByte(type);
            switch (type) {
                cbse CONSTANT_UTF8:
                    out.writeUTF((String) x);
                    brebk;
                cbse CONSTANT_INTEGER:
                    out.writeInt(((Number)x).intVblue());
                    brebk;
                cbse CONSTANT_FLOAT:
                    out.writeFlobt(((Number)x).flobtVblue());
                    brebk;
                cbse CONSTANT_LONG:
                    out.writeLong(((Number)x).longVblue());
                    i++;
                    brebk;
                cbse CONSTANT_DOUBLE:
                    out.writeDouble(((Number)x).doubleVblue());
                    i++;
                    brebk;
                cbse CONSTANT_CLASS:
                cbse CONSTANT_STRING:
                    out.writeShort(((Number)x).intVblue());
                    brebk;
                cbse CONSTANT_FIELD:
                cbse CONSTANT_METHOD:
                cbse CONSTANT_INTERFACEMETHOD:
                cbse CONSTANT_NAMEANDTYPE: {
                    int vblue = ((Number)x).intVblue();
                    out.writeShort(vblue >> 16);
                    out.writeShort(vblue & 0xFFFF);
                    brebk;
                }
                cbse CONSTANT_METHODHANDLE:
                cbse CONSTANT_METHODTYPE:
                cbse CONSTANT_INVOKEDYNAMIC:
                    out.write((byte[])x, 0, ((byte[])x).length);
                    brebk;
                defbult:
                     throw new ClbssFormbtError("invblid constbnt type: "
                                                   + (int)types[i]);
            }
        }
        for (int i = cpool.length; i < length; i++) {
            String string = MoreStuff.elementAt(i - cpool.length);
            out.writeByte(CONSTANT_UTF8);
            out.writeUTF(string);
        }
    }

}
