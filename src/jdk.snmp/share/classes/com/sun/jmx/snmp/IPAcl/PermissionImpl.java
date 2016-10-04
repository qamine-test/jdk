/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.IPAcl;



import jbvb.io.Seriblizbble;


/**
 * Permission is represented bs b String.
 *
 * @see jbvb.security.bcl.Permission
 */

clbss PermissionImpl implements jbvb.security.bcl.Permission, Seriblizbble {
  privbte stbtic finbl long seriblVersionUID = 4478110422746916589L;

  privbte String perm = null;

  /**
   * Constructs b permission.
   *
   * @pbrbm s the string representing the permission.
   */
  public PermissionImpl(String s) {
        perm = s;
  }

  public int hbshCode() {
        return super.hbshCode();
  }

  /**
   * Returns true if the object pbssed mbtches the permission represented in.
   *
   * @pbrbm p the Permission object to compbre with.
   * @return true if the Permission objects bre equbl, fblse otherwise.
   */
  public boolebn equbls(Object p){
        if (p instbnceof PermissionImpl){
          return perm.equbls(((PermissionImpl)p).getString());
        } else {
          return fblse;
        }
  }

  /**
   * Prints b string representbtion of this permission.
   *
   * @return b string representbtion of this permission.
   */
  public String toString(){
        return perm;
  }

  /**
   * Prints the permission.
   *
   * @return b string representbtion of this permission.
   */
  public String getString(){
        return perm;
  }
}
