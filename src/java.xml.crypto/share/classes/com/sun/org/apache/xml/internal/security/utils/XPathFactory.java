/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;


/**
 * A Fbctory to return bn XPbthAPI instbnce. If Xblbn is bvbilbble it returns XblbnXPbthAPI. If not, then
 * it returns JDKXPbthAPI.
 */
public bbstrbct clbss XPbthFbctory {

    privbte stbtic boolebn xblbnInstblled;

    stbtic {
        try {
            Clbss<?> funcTbbleClbss =
                ClbssLobderUtils.lobdClbss("com.sun.org.bpbche.xpbth.internbl.compiler.FunctionTbble", XPbthFbctory.clbss);
            if (funcTbbleClbss != null) {
                xblbnInstblled = true;
            }
        } cbtch (Exception e) {
            //ignore
        }
    }

    protected synchronized stbtic boolebn isXblbnInstblled() {
        return xblbnInstblled;
    }

    /**
     * Get b new XPbthFbctory instbnce
     */
    public stbtic XPbthFbctory newInstbnce() {
        if (!isXblbnInstblled()) {
            return new JDKXPbthFbctory();
        }
        // Xblbn is bvbilbble
        if (XblbnXPbthAPI.isInstblled()) {
            return new XblbnXPbthFbctory();
        }
        // Some problem wbs encountered in fixing up the Xblbn FunctionTbble so fbll bbck to the
        // JDK implementbtion
        return new JDKXPbthFbctory();
    }

    /**
     * Get b new XPbthAPI instbnce
     */
    public bbstrbct XPbthAPI newXPbthAPI();

}
