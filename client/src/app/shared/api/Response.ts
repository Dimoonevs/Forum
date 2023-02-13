export interface AuthResponse{
    
    data:  {
        token: string,
        role: string
    }
    
    
}
export interface AdminUser{
    data:[{
        id: any,
        username: string,
        role: string,
        isActive: boolean}
    ]
}
export interface ThemeResp {
    data :[ThemeArr]
}
export interface ThemeArr{
    id: any,
    name: string,
    text:string
}
export interface ThemeInfoResp{
    data: {
        commentResp: [{
            textComment: string,
            username: string,
            role: string,
            isActive: boolean
        }],
        theme: ThemeArr
    }
}

export interface MessageResp{
    data:[{
        id: any,
        email: string,
        message: string,
        isProcessed: boolean,
        statusMessage: string
    }]
}